package onextent.oemap.server.http

import onextent.oemap.server.entities.IdAble

import scala.concurrent.{ExecutionContext, Future}

class Service[T <: IdAble](implicit val executionContext: ExecutionContext) {

  var maps = Vector.empty[T]

  def createMap(map: T): Future[Option[String]] =
    Future {
      maps.find(_.id == map.id) match {
        case Some(_) => None // Conflict! id is already taken
        case None =>
          maps = maps :+ map
          Some(map.id)
      }
    }


  def getMap(id: String): Future[Option[T]] =
    Future {
      maps.find(_.id == id)
    }

  def deleteMap(id: String): Future[Unit] =
    Future {
      maps = maps.filterNot(_.id == id)
    }
}

