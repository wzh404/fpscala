package com.xeehoo.akka.student

import akka.actor.{Props, ActorSystem}
import com.xeehoo.akka.student.StudentProtocol.InitSignal
import com.xeehoo.akka.student.TeacherProtocol.QuoteRequest

/**
 * Created by wangzunhui on 2016/6/27.
 */
object StudentSimulatorApp extends App {
  val actorSystem = ActorSystem("UniversityMessageSystem")
  val teacherActorRef = actorSystem.actorOf(Props[TeacherLogActor], "TeacherLogActor")
  val studentActorRef = actorSystem.actorOf(Props(new StudentLogActor(teacherActorRef)), "StudentLogActor")
//  teacherActorRef ! QuoteRequest
  studentActorRef ! InitSignal
  Thread.sleep (2000)

  actorSystem.shutdown()
}
