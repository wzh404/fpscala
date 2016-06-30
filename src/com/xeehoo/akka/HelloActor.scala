package com.xeehoo.akka

import akka.actor.Actor
import akka.actor.Actor.Receive

/**
 * Created by wangzunhui on 2016/6/20.
 */
class HelloActor extends Actor{
  override def receive = {
    case "h" => println("hello")
    case _ => println("other")
  }
}
