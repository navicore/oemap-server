package onextent.oemap.server.services

import java.sql.Timestamp
import java.util.{Date, UUID}

import onextent.oemap.server.entities.Membership
import onextent.oemap.server.http.Service

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MembershipService extends Service[Membership] {

  def getMembership(id: UUID): Future[Option[Membership]] = get(id)

  def createMembership(mem: Membership): Future[Option[UUID]] =
    create(mem.copy(created = new Timestamp(new Date().getTime)))

  def deleteMembership(id: UUID): Future[Unit] = delete(id)

}

