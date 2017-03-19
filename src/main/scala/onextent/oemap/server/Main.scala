package onextent.oemap.server

import com.typesafe.scalalogging.LazyLogging
import scala.concurrent.duration._
import akka.actor._
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout

object Main extends LazyLogging with Conf with App with RestInterface {

  implicit val system = ActorSystem("quiz-management-service")
  implicit val materializer = ActorMaterializer()


  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(10 seconds)

  val api = routes

  Http().bindAndHandle(handler = api, interface = host, port = port) map { binding =>
    logger.info(s"REST interface bound to ${binding.localAddress}") } recover { case ex =>
    logger.error(s"REST interface could not bind to $host:$port", ex.getMessage)
  }
}

