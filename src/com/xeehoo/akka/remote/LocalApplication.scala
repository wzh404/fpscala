package com.xeehoo.akka.remote

import java.io.File

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

/**
 * Created by wangzunhui on 2016/7/6.
 */
object LocalApplication {
  def main(args: Array[String]) {
    val configFile = getClass.getClassLoader.getResource("local.conf").getFile
    val config = ConfigFactory.parseFile(new File(configFile))
    val system = ActorSystem("ClientSystem",config)
    val localActor = system.actorOf(Props[LocalActor], name="local")
  }
}
