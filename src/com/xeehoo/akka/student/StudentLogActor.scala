package com.xeehoo.akka.student

import akka.actor._
import akka.actor.Actor.Receive
import com.xeehoo.akka.student.StudentProtocol.InitSignal
import com.xeehoo.akka.student.TeacherProtocol.{QuoteResponse, QuoteRequest}

/**
 * Created by wangzunhui on 2016/6/27.
 */
class StudentLogActor(teacherActorRef: ActorRef) extends Actor with ActorLogging {
  val child = context.actorOf(Props[TeacherLogActor], "bbb")

  def receive = {
    case InitSignal => {
      teacherActorRef ! QuoteRequest
      log.info(child.path.toString);
    }

    case QuoteResponse(quoteString) => {
      log.info("Received QuoteResponse from Teacher")
      log.info(s"Printing from Student Actor $quoteString")
    }
  }
}
