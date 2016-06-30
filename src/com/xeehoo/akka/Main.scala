package com.xeehoo.akka

import akka.actor.{ActorSystem, Props}

/**
 * Created by wangzunhui on 2016/6/21.
 */
object Main {
  def main(args: Array[String]) = {
    val system = ActorSystem("HelloSystem")
    val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
    helloActor.!("h")

    val h = new HelloClass();
    h.a

    implicit def double2Int(d: Double) = d.toInt
    val num: Int = 3.5

    val p = new Person("wang", 23)
    println(p + 1)

    val a = new A with TA
    println(a.m)
  }
}

class A { ref =>
  val x = 2
  def foo = ref.x + this.x
}

trait TA { ref : A =>
  def m = ref.foo
}
