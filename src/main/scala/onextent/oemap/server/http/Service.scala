package onextent.oemap.server.http

import java.util.UUID

import onextent.oemap.server.entities.IdAble

import scala.concurrent.{ExecutionContext, Future}

class Service[T <: IdAble](implicit val executionContext: ExecutionContext) {

  var entries = Vector.empty[T]

  def create(entry: T): Future[Option[UUID]] =
    Future {
      entries.find(_.id == entry.id) match {
        case Some(_) => None // Conflict! id is already taken
        case None =>
          entries = entries :+ entry
          Some(entry.id)
      }
    }


  def get(id: UUID): Future[Option[T]] =
    Future {
      entries.find(_.id == id)
    }

  def delete(id: UUID): Future[Unit] =
    Future {
      entries = entries.filterNot(_.id == id)
    }
}

