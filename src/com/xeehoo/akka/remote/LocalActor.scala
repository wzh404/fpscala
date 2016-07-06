package com.xeehoo.akka.remote

import akka.actor.Actor.Receive
import akka.actor._

/**
 * Created by wangzunhui on 2016/7/6.
 */
class LocalActor extends Actor{
  override def preStart(): Unit = {
    val remoteActor = context.actorSelection("akka.tcp://RemoteSystem@127.0.0.1:5150/user/remote")
    println("That 's remote:" + remoteActor)
    remoteActor ! "hi"
  }

  override def receive: Receive = {
    case msg:String => {
      println("got message from remote" + msg)
    }
  }
}
