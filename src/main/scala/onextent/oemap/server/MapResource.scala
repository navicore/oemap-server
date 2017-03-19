package onextent.oemap.server

import akka.http.scaladsl.server.Route
import onextent.oemap.server.entities.OeMap
import onextent.oemap.server.http.{JsonResource, Service}

trait MapResource extends JsonResource {

  val mapService: Service[OeMap]

  val emptyStatus = 409
  val definedStatus = 201

  def mapRoutes: Route = pathPrefix("maps") {
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
