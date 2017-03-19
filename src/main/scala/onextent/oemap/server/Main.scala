package onextent.oemap.server

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.duration._
import akka.actor._
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}

object Main extends LazyLogging with App with RestInterface {

  val config: Config = ConfigFactory.load().getConfig("main")
  val logLevel: String = config.getString("logLevel")
  val appName: String = config.getString("appName")
  val host: String = config.getString("host")
  val port: Int = config.getInt("port")

  implicit val system = ActorSystem("map-management-service")
  implicit val materializer = ActorMaterializer()

  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(10 seconds)

  val api = routes

  Http().bindAndHandle(handler = api, interface = host, port = port) map {
    binding =>
      logger.info(s"REST interface bound to ${binding.localAddress}")
  } recover {
    case ex =>
      logger.error(s"REST interface could not bind to $host:$port",
                   ex.getMessage)
  }
}

