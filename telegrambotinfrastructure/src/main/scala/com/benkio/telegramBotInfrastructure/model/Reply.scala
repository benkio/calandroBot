package com.benkio.telegramBotInfrastructure.model

import info.mukel.telegrambot4s.models.Message
import com.benkio.telegramBotInfrastructure.default.Actions.Action
import scala.concurrent.Future

sealed trait Reply

final case class TextReply(
    text: Message => List[String],
    replyToMessage: Boolean = false
) extends Reply

sealed trait MediaFile extends Reply {
  def filepath: String
  def filename: String  = filepath.split('/').last
  def extension: String = filename.takeRight(4)
}

final case class Mp3File private[model] (filepath: String) extends MediaFile {
  require(filepath.endsWith(".mp3"))
}

final case class GifFile private[model] (filepath: String) extends MediaFile {
  require(filepath.endsWith(".gif"))
}

final case class PhotoFile private[model] (filepath: String) extends MediaFile {
  require(List(".jpg", ".png").exists(filepath.endsWith(_)))
}

object MediaFile {

  def apply(filepath: String): MediaFile = filepath match {
    case s if s.endsWith(".mp3")                         => Mp3File(s)
    case s if s.endsWith(".gif")                         => GifFile(s)
    case s if List(".jpg", ".png").exists(s.endsWith(_)) => PhotoFile(s)
    case _ =>
      throw new IllegalArgumentException(
        s"filepath extension not recognized: $filepath \n allowed extensions: mp3, gif, jpg, png"
      )
  }
}

object Reply {

  def toMessageReply(f: Reply, m: Message)(
      implicit audioAction: Action[Mp3File],
      gifAction: Action[GifFile],
      photoAction: Action[PhotoFile],
      textAction: Action[TextReply]
  ): Future[Message] = f match {
    case mp3 @ Mp3File(_)       => audioAction(mp3)(m)
    case gif @ GifFile(_)       => gifAction(gif)(m)
    case photo @ PhotoFile(_)   => photoAction(photo)(m)
    case text @ TextReply(_, _) => textAction(text)(m)
  }

}
