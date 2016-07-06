package com.xeehoo.akka.remote

import java.io.File

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

/**
 * Created by wangzunhui on 2016/7/6.
 */
object RemoteApplicaion {
  def main(args: Array[String]): Unit = {
    val configFile = getClass.getClassLoader.getResource("application.conf").getFile

    //parse the config
    val config = ConfigFactory.parseFile(new File(configFile))

    //create an actor system with that config
    val system = ActorSystem("RemoteSystem" , config)

    //create a remote actor from actorSystem
    val remote = system.actorOf(Props[RemoteActor], name="remote")
    println("remote is ready")
  }
}
