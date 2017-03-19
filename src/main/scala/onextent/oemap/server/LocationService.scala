package onextent.oemap.server

import java.util.UUID

import onextent.oemap.server.entities.Location
import onextent.oemap.server.http.Service

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LocationService extends Service[Location] {

  def getLocation(id: UUID): Future[Option[Location]] = get(id)
  def createLocation(loc: Location): Future[Option[UUID]] = create(loc)
  def deleteLocation(id: UUID): Future[Unit] = delete(id)

}

