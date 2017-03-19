package onextent.oemap.server

import java.util.UUID

import onextent.oemap.server.entities.OeMap
import onextent.oemap.server.http.Service

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MapService extends Service[OeMap] {

  def getMap(id: UUID): Future[Option[OeMap]] = get(id)
  def createMap(map: OeMap): Future[Option[UUID]] = create(map)
  def deleteMap(id: UUID): Future[Unit] = delete(id)

}
