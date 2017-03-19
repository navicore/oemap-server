package onextent.oemap.server.http

import java.util.UUID

import onextent.oemap.server.entities.IdAble

import scala.concurrent.{ExecutionContext, Future}

class Service[T <: IdAble](implicit val executionContext: ExecutionContext) {

  var maps = Vector.empty[T]

  def create(map: T): Future[Option[UUID]] =
    Future {
      maps.find(_.id == map.id) match {
        case Some(_) => None // Conflict! id is already taken
        case None =>
          maps = maps :+ map
          Some(map.id)
      }
    }


  def get(id: UUID): Future[Option[T]] =
    Future {
      maps.find(_.id == id)
    }

  def delete(id: UUID): Future[Unit] =
    Future {
      maps = maps.filterNot(_.id == id)
    }
}

