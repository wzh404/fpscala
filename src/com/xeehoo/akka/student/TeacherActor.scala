package com.xeehoo.akka.student

import akka.actor.Actor
import akka.actor.Actor.Receive
import com.xeehoo.akka.student.TeacherProtocol.{QuoteResponse, QuoteRequest}

/**
 * Created by wangzunhui on 2016/6/27.
 */
class TeacherActor extends Actor {
  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  override def receive = {
    case QuoteRequest => {

      import util.Random

      //Get a random Quote from the list and construct a response
      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))

      println(quoteResponse)
    }
  }
}
