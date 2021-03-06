package root

import com.benkio.telegramBotInfrastructure.botCapabilities._
import info.mukel.telegrambot4s.models.Message
import com.benkio.telegramBotInfrastructure._
import io.github.todokr.Emojipolation._
import com.benkio.telegramBotInfrastructure.model._
import scala.util.Random

object CalandroBot extends BotSkeleton {

  override val resourceSource: ResourceSource = All("calandro.db")

  override lazy val commandRepliesData: List[ReplyBundleCommand] = List(
    ReplyBundleCommand(CommandTrigger("porcoladro"), List(MediaFile("PorcoLadro.mp3"))),
    ReplyBundleCommand(CommandTrigger("unoduetre"), List(MediaFile("unoduetre.mp3"))),
    ReplyBundleCommand(CommandTrigger("ancorauna"), List(MediaFile("AncoraUnaDoveLaMetto.mp3"))),
    ReplyBundleCommand(CommandTrigger("lacipolla"), List(MediaFile("CipollaCalandrica.mp3"))),
    ReplyBundleCommand(CommandTrigger("lavorogiusto"), List(MediaFile("IlLavoroVaPagato.mp3"))),
    ReplyBundleCommand(CommandTrigger("motivazioniinternet"), List(MediaFile("InternetMotivazioniCalandriche.mp3"))),
    ReplyBundleCommand(CommandTrigger("cazzomene"), List(MediaFile("IoSonVaccinato.mp3"))),
    ReplyBundleCommand(CommandTrigger("arrivoarrivo"), List(MediaFile("SubmissionCalandra.mp3"))),
    ReplyBundleCommand(CommandTrigger("vaginadepilata"), List(MediaFile("VaginaDepilataCalandra.mp3"))),
    ReplyBundleCommand(CommandTrigger("whawha_fallout4"), List(MediaFile("waawahaawha.mp3"))),
    ReplyBundleCommand(CommandTrigger("whawha_short"), List(MediaFile("wwhaaawhaaa Singolo.mp3"))),
    ReplyBundleCommand(CommandTrigger("daccordissimo"), List(MediaFile("d_accordissimo.mp3"))),
    ReplyBundleCommand(CommandTrigger("stocazzo"), List(MediaFile("stocazzo.mp3"))),
    ReplyBundleCommand(CommandTrigger("cazzodibudda"), List(MediaFile("cazzoDiBudda.mp3"))),
    ReplyBundleCommand(CommandTrigger("personapulita"), List(MediaFile("personaPulita.mp3"))),
    ReplyBundleCommand(CommandTrigger("losquirt"), List(MediaFile("loSquirt.mp3"))),
    ReplyBundleCommand(CommandTrigger("fuoridalmondo"), List(MediaFile("fuoriDalMondo.mp3"))),
    ReplyBundleCommand(CommandTrigger("qualitaOlive"), List(MediaFile("qualitaOlive.mp3"))),
    ReplyBundleCommand(CommandTrigger("gioielli"), List(MediaFile("gioielli.mp3"))),
    ReplyBundleCommand(CommandTrigger("risata"), List(MediaFile("risata.mp3"))),
    ReplyBundleCommand(CommandTrigger("sonocosternato"), List(MediaFile("sonoCosternato.mp3"))),
    ReplyBundleCommand(CommandTrigger("demenza"), List(MediaFile("laDemenzaDiUnUomo.mp3"))),
    ReplyBundleCommand(CommandTrigger("wha"), List(MediaFile("whaSecco.mp3"))),
    ReplyBundleCommand(CommandTrigger("imparatounafava"), List(MediaFile("imparatoUnaFava.mp3"))),
    ReplyBundleCommand(CommandTrigger("lesbiche"), List(MediaFile("SieteLesbiche.mp3"))),
    ReplyBundleCommand(CommandTrigger("firstlesson"), List(MediaFile("firstLessonPlease.mp3"))),
    ReplyBundleCommand(CommandTrigger("noprogrammato"), List(MediaFile("noGrazieProgrammato.mp3"))),
    ReplyBundleCommand(CommandTrigger("fiammeinferno"), List(MediaFile("fiamme.mp3"))),
    ReplyBundleCommand(
      CommandTrigger("randomcard"),
      ResourceSource.selectResourceAccess(All("calandro.db")).getResourcesByKind(".jpg"),
      replySelection = RandomSelection
    )
  )

  override lazy val messageRepliesData: List[ReplyBundleMessage] = List(
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue("sbrighi"))),
      text = TextReply((m: Message) => List("Passo"), false)
    ),
    ReplyBundleMessage(
      TextTrigger(
        List(
          StringTextTriggerValue("gay"),
          StringTextTriggerValue("frocio"),
          StringTextTriggerValue("culattone"),
          StringTextTriggerValue("ricchione")
        )
      ),
      text = TextReply((m: Message) => List("CHE SCHIFO!!!"), false)
    ),
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue("caldo"), StringTextTriggerValue("scotta"))),
      text = TextReply((m: Message) => List("Come i carbofreni della Brembo!!"), false)
    ),
    ReplyBundleMessage(
      TextTrigger(
        List(StringTextTriggerValue("ciao"), StringTextTriggerValue("buongiorno"), StringTextTriggerValue("salve"))
      ),
      text = TextReply((m: Message) => List("Buongiorno Signori"), false)
    ),
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue("film"))),
      text = TextReply((m: Message) => List("Lo riguardo volentieri"), false)
    ),
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue("stasera"), StringTextTriggerValue("?"))),
      text = TextReply((m: Message) => List("Facciamo qualcosa tutti assieme?"), false),
      matcher = ContainsAll
    ),
    ReplyBundleMessage(
      TextTrigger(
        List(
          StringTextTriggerValue(" hd"),
          StringTextTriggerValue("nitido"),
          StringTextTriggerValue("nitidezza"),
          StringTextTriggerValue("alta definizione")
        )
      ),
      text = TextReply((m: Message) => List("Eh sì, vedi...si nota l'indecisione dell'immagine"), false)
    ),
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue("qualità"))),
      text = TextReply((m: Message) => List("A 48x masterizza meglio"), false)
    ),
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue("macchina"), StringTextTriggerValue("automobile"))),
      text = TextReply((m: Message) => List("Hai visto l'ultima puntata di \"Top Gear\"?"), false)
    ),
    ReplyBundleMessage(
      TextTrigger(
        List(
          StringTextTriggerValue(" figa "),
          StringTextTriggerValue(" fregna "),
          StringTextTriggerValue(" gnocca "),
          StringTextTriggerValue(" patacca ")
        )
      ),
      text = TextReply((m: Message) => List("Io so come fare con le donne...ho letto tutto..."), false)
    ),
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue("ambulanza"), StringTextTriggerValue(emoji":ambulance:"))),
      text = TextReply(
        (m: Message) =>
          List(
            emoji":triumph: :horns_sign: :hand_with_index_and_middle_fingers_crossed: :hand_with_index_and_middle_fingers_crossed: :horns_sign: :triumph:"
          ),
        false
      )
    ),
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue("pc"), StringTextTriggerValue("computer"))),
      text = TextReply((m: Message) => List("Il fisso performa meglio rispetto al portatile!!!"), false)
    ),
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue("videogioc"), StringTextTriggerValue(emoji":video_game:"))),
      text = TextReply(
        (m: Message) =>
          List(s"GIOCHI PER IL MIO PC #${Random.nextInt(Int.MaxValue)}??No ma io non lo compro per i giochi!!!"),
        false
      )
    ),
    ReplyBundleMessage(
      TextTrigger(List(StringTextTriggerValue(" hs"), StringTextTriggerValue("hearthstone"))),
      text = TextReply((m: Message) => List("BASTA CON QUESTI TAUNT!!!"), false)
    ),
    ReplyBundleMessage(
      MessageLengthTrigger(280),
      text = TextReply(
        (msg: Message) => List(s"""wawaaa rischio calandrico in aumento(${msg.text.getOrElse("").length} / 280)"""),
        true
      )
    )
  )
}
