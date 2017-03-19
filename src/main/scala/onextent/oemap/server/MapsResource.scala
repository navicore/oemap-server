package onextent.oemap.server

import akka.http.scaladsl.server.Route
import onextent.oemap.server.entities.{Location, Membership, OeMap, Person}
import onextent.oemap.server.http.JsonResource
import onextent.oemap.server.services.{LocationService, MapService, MembershipService, PersonService}

trait MapsResource extends JsonResource {

  val mapService: MapService
  val personService: PersonService
  val locationService: LocationService
  val membershipService: MembershipService

  val emptyStatus = 409
  val definedStatus = 201

  // scalastyle:off
  def mapsRoutes: Route = pathPrefix("maps") {

    pathPrefix("membership") {
      path(JavaUUID) { id =>
        get {
          complete(membershipService.getMembership(id))
        }
      } ~
      pathEnd {
        post {
          entity(as[Membership]) { membership =>
            completeWithLocationHeader(
              resourceId = membershipService.createMembership(membership),
              ifDefinedStatus = definedStatus,
              ifEmptyStatus = emptyStatus
            )
          }
        }
      }
    } ~
    pathPrefix("location") {
      path(JavaUUID) { id =>
        get {
          complete(locationService.getLocation(id))
        }
      } ~
      pathEnd {
        post {
          entity(as[Location]) { loc =>
            completeWithLocationHeader(
              resourceId = locationService.createLocation(loc),
              ifDefinedStatus = definedStatus,
              ifEmptyStatus = emptyStatus
            )
          }
        }
      }
    } ~
    pathPrefix("person") {
      path(JavaUUID) { id =>
        get {
          complete(personService.getPerson(id))
        }
      } ~
      pathEnd {
        post {
          entity(as[Person]) { person =>
            completeWithLocationHeader(
              resourceId = personService.createPerson(person),
              ifDefinedStatus = definedStatus,
              ifEmptyStatus = emptyStatus
            )
          }
        }
      }
    } ~
    pathPrefix("map") {
      path(JavaUUID) { id =>
        get {
          complete(mapService.getMap(id))
        }
      } ~
      pathEnd {
        post {
          entity(as[OeMap]) { map =>
            completeWithLocationHeader(
              resourceId = mapService.createMap(map),
              ifDefinedStatus = definedStatus,
              ifEmptyStatus = emptyStatus
            )
          }
        }
      }
    }

  }
}

