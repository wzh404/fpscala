package com.xeehoo.akka.cluster

import akka.actor.Actor.Receive
import akka.actor._
import akka.cluster.ClusterEvent._
import akka.cluster.{Member, MemberStatus}
import com.typesafe.config.ConfigFactory

import scala.util.parsing.json.JSONObject

/**
 * Created by wangzunhui on 2016/7/7.
 */
class EventInterceptor extends ClusterRoledWorker{
  @volatile var interceptedRecords : Int = 0
  val IP_PATTERN = "[^\\s]+\\s+\\[([^\\]]+)\\].+\"(\\d+\\.\\d+\\.\\d+\\.\\d+)\"".r

  def receive = {
    case MemberUp(member) =>
    log.info("Member is Up: {}", member.address)
    register(member, getCollectorPath)
    case state: CurrentClusterState =>
      // 如果加入Akka集群的成员节点是Up状态，并且是collector角色，则调用register向collector进行注册
      state.members.filter(_.status == MemberStatus.Up) foreach(register(_, getCollectorPath))
    case UnreachableMember(member) =>
    log.info("Member detected as Unreachable: {}", member)
    case MemberRemoved(member, previousStatus) =>
    log.info("Member is Removed: {} after {}", member.address, previousStatus)
    case _: MemberEvent => // ignore
    case Registration => {
      context watch sender
      workers = workers :+ sender
      log.info("Processor registered: " + sender)
      log.info("Registered processors: " + workers.size)
    }

    case Terminated(processingActorRef) =>
    workers = workers.filterNot(_ == processingActorRef)
    case NginxRecord(sourceHost, eventCode, line) => {
//      val (isIpInBlackList, data) = checkRecord(eventCode, line)
//      if(!isIpInBlackList) {
//      val data: JSONObject = new JSONObject()
      var realIp : String = "192.168.10.10"
      IP_PATTERN.findFirstMatchIn(line).foreach{ m =>
        val rawDt = m.group(1)
//        val dt = DatetimeUtils.format(rawDt)
        realIp = m.group(2)
      }
      interceptedRecords += 1
      log.info(interceptedRecords + " = " + realIp)

//        if(workers.size > 0) {
//          val processorIndex = (if (interceptedRecords < 0) 0 else interceptedRecords) % workers.size
//          workers(processorIndex) ! FilteredRecord(sourceHost, eventCode, line, "2016-07-07", realIp)
//          log.info("Details: processorIndex=" + processorIndex + ", processors=" + workers.size)
//        }
//        log.info("Intercepted data: data=" + data)
//      } else {
//        log.info("Discarded: " + line)
//      }
    }
  }

  def getCollectorPath(member: Member): ActorPath = {
    RootActorPath(member.address) / "user" / "collectingActor"
  }
}

object EventInterceptor extends App {
  Seq("2851","2852").foreach { port =>
    val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port)
      .withFallback(ConfigFactory.parseString("akka.cluster.roles = [interceptor]"))
      .withFallback(ConfigFactory.load())
    val system = ActorSystem("event-cluster-system", config)
    val processingActor = system.actorOf(Props[EventInterceptor], name = "interceptingActor")
    system.log.info("Processing Actor: " + processingActor)
  }
}

