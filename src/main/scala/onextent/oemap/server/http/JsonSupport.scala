package onextent.oemap.server.http

import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.ext.{JavaTypesSerializers, JodaTimeSerializers}
import org.json4s.native.Serialization
import org.json4s.{Formats, NoTypeHints, native}

trait JsonSupport extends Json4sSupport {

  implicit val serialization = native.Serialization

  implicit def json4sFormats: Formats = Serialization.formats(NoTypeHints) ++ JavaTypesSerializers.all ++ JodaTimeSerializers.all ++ CustomSerializers.all

}

