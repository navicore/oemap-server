package onextent.oemap.server

import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext

trait RestInterface extends Resources {

  implicit def executionContext: ExecutionContext

  lazy val mapService = new MapService
  lazy val personService = new PersonService
  lazy val locationService = new LocationService
  lazy val membershipService = new MembershipService

  val routes: Route = mapsRoutes

}

trait Resources extends MapsResource

