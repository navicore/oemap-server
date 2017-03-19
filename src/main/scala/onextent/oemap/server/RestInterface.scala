package onextent.oemap.server

import scala.concurrent.ExecutionContext
import akka.http.scaladsl.server.Route
import onextent.oemap.server.entities.OeMap
import onextent.oemap.server.http.Service

trait RestInterface extends Resources {

  implicit def executionContext: ExecutionContext

  lazy val mapService = new Service[OeMap]

  val routes: Route = mapRoutes

}

trait Resources extends MapResource

