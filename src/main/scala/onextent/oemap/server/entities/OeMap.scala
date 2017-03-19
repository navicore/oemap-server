package onextent.oemap.server.entities

trait IdAble {val id: String}

case class Person(id: String, name: String, description: String) extends IdAble

case class OeMap(id: String, ownerId: String, title: String, description: String) extends IdAble

case class Membership(id: String, memberId: String, mapId: String, description: String, role: String) extends IdAble

