package onextent.oemap.server.entities

import java.util.UUID

trait IdAble { val id: UUID }


case class Person(id: UUID, name: String, description: String) extends IdAble


case class OeMap(id: UUID,
                 ownerId: UUID,
                 title: String,
                 description: String)
    extends IdAble


case class Membership(id: UUID,
                      personId: UUID,
                      mapId: UUID,
                      description: String,
                      role: String)
    extends IdAble


case class Location(id: UUID,
                    personId: UUID,
                    mapId: UUID,
                    description: String,
                    timestamp: Long,
                    lat: Double,
                    lon: Double)
    extends IdAble

