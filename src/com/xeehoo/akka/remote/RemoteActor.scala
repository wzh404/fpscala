package com.xeehoo.akka.remote


import akka.actor.Actor.Receive
import akka.actor._

/**
 * Created by wangzunhui on 2016/7/6.
 */
class RemoteActor extends Actor{
  override def receive: Receive = {
    case msg: String => {
      println("remote received " + msg + " from " + sender)
      sender ! "hi"
    }
    case _ => println("Received unknown msg ")
  }
}
