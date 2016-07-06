package com.xeehoo.akka.cluster

import akka.actor.Actor.Receive
import akka.actor.{ActorPath, ActorRef, ActorLogging, Actor}
import akka.cluster.ClusterEvent.{MemberEvent, UnreachableMember, MemberUp, InitialStateAsEvents}
import akka.cluster.{Cluster, Member}

/**
 * Created by wangzunhui on 2016/7/6.
 *
 * http://shiyanjun.cn/archives/1186.html
 */
abstract class ClusterRoledWorker extends Actor with ActorLogging{
  val cluster = Cluster(context.system)
  var workers = IndexedSeq.empty[ActorRef]

  override def preStart(): Unit = {
    cluster.subscribe(self, initialStateMode = InitialStateAsEvents, classOf[MemberUp], classOf[UnreachableMember], classOf[MemberEvent])
  }

  override def postStop(): Unit = cluster.unsubscribe(self)

  /**
    * 下游子系统节点发送注册消息
    */
  def register(member: Member, createPath: (Member) => ActorPath): Unit = {
    val actorPath = createPath(member)
    log.info("Actor path: " + actorPath)
    val actorSelection = context.actorSelection(actorPath)
    actorSelection ! Registration
  }
}
