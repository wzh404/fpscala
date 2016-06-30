package com.xeehoo.akka.student

import akka.actor.{ActorLogging, Actor}
import akka.actor.Actor.Receive
import com.xeehoo.akka.student.TeacherProtocol.{QuoteResponse, QuoteRequest}

/**
 * Created by wangzunhui on 2016/6/27.
 */
class TeacherLogActor extends Actor with ActorLogging {
  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  def receive = {

    case QuoteRequest => {

      import util.Random

      //get a random element (for now)
      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))
      log.info(quoteResponse.toString())
      sender ! quoteResponse
    }
  }
}
