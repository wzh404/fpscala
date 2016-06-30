package com.xeehoo.intelligence

/**
 * Created by wangzunhui on 2016/5/30.
 */
object Intelligence {
  def main(args: Array[String]) = {
    testPearson()
  }

  def testEuclid(): Unit ={
    val x = Map[String, Double]("lady"->3.0, "snakes"->3.5, "just"->1.5, "superman"->5.0, "dupree"->3.5, "night"->3.0)
    val y = Map[String, Double]("lady"->2.5, "snakes"->3.5, "just"->3.0, "superman"->3.5, "dupree"->2.5, "night"->3.0)
    val e = new Euclidean();
    println(e.distance(x, y));
  }

  def testPearson(): Unit ={
    val x = Map[String, Double]("lady"->3.0, "snakes"->3.5, "just"->1.5, "superman"->5.0, "dupree"->3.5, "night"->3.0)
    val y = Map[String, Double]("lady"->2.5, "snakes"->3.5, "just"->3.0, "superman"->3.5, "dupree"->2.5, "night"->3.0)
    val e = new Pearson();
    println(e.distance(x, y));
  }
}
