package onextent.oemap.server.http

import java.text.SimpleDateFormat

import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.ext.JavaTypesSerializers
import org.json4s.{DefaultFormats, Formats, native}

trait JsonSupport extends Json4sSupport {

  implicit val serialization = native.Serialization

  implicit def json4sFormats: Formats = customDateFormat ++ JavaTypesSerializers.all

  val customDateFormat = new DefaultFormats {
    override def dateFormatter = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss'Z'" )
  }
}

