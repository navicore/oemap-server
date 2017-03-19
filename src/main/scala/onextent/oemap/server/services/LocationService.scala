package onextent.oemap.server.services

import java.sql.Timestamp
import java.util.{Date, UUID}

import onextent.oemap.server.entities.Location
import onextent.oemap.server.http.Service

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LocationService extends Service[Location] {

  def getLocation(id: UUID): Future[Option[Location]] = get(id)

  def createLocation(loc: Location): Future[Option[UUID]] =
    create(loc.copy(created = new Timestamp(new Date().getTime)))

  def deleteLocation(id: UUID): Future[Unit] = delete(id)

}

