package onextent.oemap.server.services

import java.sql.Timestamp
import java.util.{Date, UUID}

import onextent.oemap.server.entities.Person
import onextent.oemap.server.http.Service

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PersonService extends Service[Person] {

  def getPerson(id: UUID): Future[Option[Person]] = get(id)

  def createPerson(person: Person): Future[Option[UUID]] =
    create(person.copy(created = new Timestamp(new Date().getTime)))

  def deletePerson(id: UUID): Future[Unit] = delete(id)

}

