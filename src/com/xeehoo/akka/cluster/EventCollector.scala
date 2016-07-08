package com.xeehoo.akka.cluster

import akka.actor.Actor.Receive
import akka.actor.Terminated
import akka.cluster.ClusterEvent.{MemberEvent, MemberRemoved, UnreachableMember, MemberUp}

/**
 * Created by wangzunhui on 2016/7/7.
 */
class EventCollector extends ClusterRoledWorker {
  @volatile var recordCounter : Int = 0

  override def receive: Receive = {
    case MemberUp(member) =>
      log.info("Member is Up: {}", member.address)

    case UnreachableMember(member) =>
      log.info("Member detected as Unreachable: {}", member)

    case MemberRemoved(member, previousStatus) =>
      log.info("Member is Removed: {} after {}", member.address, previousStatus)

    case _: MemberEvent => // ignore


    case Registration => {
      // watch发送注册消息的interceptor，如果对应的Actor终止了，会发送一个Terminated消息
      context watch sender
      workers = workers :+ sender
      log.info("Interceptor registered: " + sender)
      log.info("Registered interceptors: " + workers.size)
    }

    case Terminated(interceptingActorRef) =>

      // interceptor终止，更新缓存的ActorRef
      workers = workers.filterNot(_ == interceptingActorRef)

    case RawNginxRecord(sourceHost, line) => {
      val eventCode = "eventcode=(\\d+)".r.findFirstIn(line).get
      log.info("Raw message: eventCode=" + eventCode + ", sourceHost=" + sourceHost + ", line=" + line)
      recordCounter += 1
      if(workers.size > 0) {
        val interceptorIndex = (if(recordCounter < 0) 0 else recordCounter) % workers.size
        workers(interceptorIndex) ! NginxRecord(sourceHost, eventCode, line)
        log.info("Details: interceptorIndex=" + interceptorIndex + ", interceptors=" + workers.size)
      }
    }
  }
}
