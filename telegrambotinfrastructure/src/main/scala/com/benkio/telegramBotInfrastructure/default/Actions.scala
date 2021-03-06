package com.benkio.telegramBotInfrastructure.default

import com.benkio.telegramBotInfrastructure.botCapabilities.ResourceSource
import info.mukel.telegrambot4s._
import methods._
import models._
import java.nio.file.Files
import java.nio.file.Path

import info.mukel.telegrambot4s.api.declarative.Messages
import info.mukel.telegrambot4s.api.ChatActions
import info.mukel.telegrambot4s.api.RequestHandler
import com.benkio.telegramBotInfrastructure.default.Actions.Action
import com.benkio.telegramBotInfrastructure.model._

import scala.concurrent.Future

trait DefaultActions extends Messages with ChatActions {

  val resourceSource: ResourceSource

  val dispatchApiRequestMultipartConstructor = Map(
    classOf[Mp3File]   -> ((chatId: Long, inputFile: InputFile) => SendAudio(chatId, inputFile)),
    classOf[PhotoFile] -> ((chatId: Long, inputFile: InputFile) => SendPhoto(chatId, inputFile)),
    classOf[GifFile]   -> ((chatId: Long, inputFile: InputFile) => SendDocument(chatId, inputFile))
  )
  val default = (chatId: Long, inputFile: InputFile) => SendDocument(chatId, inputFile)
  def wrapApiRequestMultipartConstructor[MediaFile](
      implicit mf: ClassManifest[MediaFile]
  ): (Long, InputFile) => ApiRequestMultipart[Message] =
    dispatchApiRequestMultipartConstructor.find(_._1.isAssignableFrom(mf.erasure)).map(_._2).getOrElse(default)

  lazy val getResourceData: String => Array[Byte] = ResourceSource.selectResourceAccess(resourceSource).getResource _

  implicit val sendPhoto: Action[PhotoFile] =
    Actions.sendMedia(
      getResourceData,
      uploadingPhoto(_),
      request,
      wrapApiRequestMultipartConstructor[PhotoFile]
    )

  implicit val sendAudio: Action[Mp3File] =
    Actions.sendMedia(
      getResourceData,
      uploadingAudio(_),
      request,
      wrapApiRequestMultipartConstructor[Mp3File]
    )

  implicit val sendGif: Action[GifFile] =
    Actions.sendMedia(
      getResourceData,
      uploadingDocument(_),
      request,
      wrapApiRequestMultipartConstructor[GifFile]
    )

  implicit val sendReply: Action[TextReply] =
    Actions.sendReply(
      typing(_),
      request
    )

}

object Actions {

  type Action[T <: Reply] =
    T => Message => Future[Message]

  def sendMedia(
      getResourceData: => String => Array[Byte],
      uploadingDocument: Message => Future[Boolean],
      requestHandler: RequestHandler,
      requestBuilder: (Long, InputFile) => ApiRequestMultipart[Message]
  ): Action[MediaFile] =
    (mediaFile: MediaFile) =>
      (msg: Message) => {
        uploadingDocument(msg)
        val byteArray: Array[Byte] = getResourceData(mediaFile.filepath)
        val inputFile              = InputFile(mediaFile.filepath, byteArray)
        requestHandler(requestBuilder(msg.source, inputFile))
      }

  def sendReply(typing: Message => Future[Boolean], requestHandler: RequestHandler): Action[TextReply] =
    (t: TextReply) =>
      (msg: Message) => {
        val replyToMessageId: Option[Int] =
          if (t.replyToMessage) Some(msg.messageId) else None
        val replyContent = t.text(msg).fold("")(_ + "\n" + _)
        if (!replyContent.isEmpty) {
          typing(msg)
          requestHandler(
            SendMessage(
              msg.source,
              replyContent,
              None,
              None,
              None,
              replyToMessageId,
              None
            )
          )
        } else
          Future.successful[Message](
            Message(
              messageId = 0,
              date = 0,
              chat = Chat(id = 0, `type` = ChatType.Private)
            )
          )
      }
}
