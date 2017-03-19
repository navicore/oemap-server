package onextent.oemap.server

import com.typesafe.config.{Config, ConfigFactory}

trait Conf {

  val config: Config   = ConfigFactory.load().getConfig("main")
  val logLevel: String = config.getString("logLevel")
  val appName: String  = config.getString("appName")
  val host: String     = config.getString("host")
  val port: Int        = config.getInt("port")

}

