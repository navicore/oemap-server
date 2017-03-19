package onextent.oemap.server.entities

import java.sql.Timestamp
import java.util.UUID

trait IdAble { val id: UUID; val created: Timestamp }

case class Person(id: UUID,
                  name: String,
                  description: String,
                  created: Timestamp)
    extends IdAble

case class OeMap(id: UUID,
                 ownerId: UUID,
                 title: String,
                 created: Timestamp,
                 description: String)
    extends IdAble

case class Membership(id: UUID,
                      personId: UUID,
                      mapId: UUID,
                      description: String,
                      created: Timestamp,
                      role: String)
    extends IdAble

case class Location(id: UUID,
                    personId: UUID,
                    mapId: UUID,
                    description: String,
                    created: Timestamp,
                    lat: Double,
                    lon: Double)
    extends IdAble
