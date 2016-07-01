package com.xeehoo.intelligence

import scala.collection.mutable.ArrayBuffer

/**
 * Created by wangzunhui on 2016/6/30.
 *
 *
 *   1 2 3 4 0 1
 *   1 4 2 0 3 1
 *
 *       s1  s2  s3  s4
 *   h1  1   3   0   1
 *   h2  0   2   0   0
 *
 *   s1 == s4
 */
class Jaccard {

  def cal(r: Array[Int], a: Array[Array[Int]]):Int = {
    val h1 : Array[Int] = r.map(x=>hash1(x))
    h1.foreach(x => print(x + " "))
    println()

    val h2 = r.map(x=>hash2(x))
    h2.foreach(x => print(x + " "))
    println()

    var h = a.map(x => min(x, h1))
    h.foreach(x =>print(x + " "))
    println()


    h = a.map(x => min(x, h2))
    h.foreach(x =>print(x + " "))

    return 0
  }

  def min(a : Array[Int], h: Array[Int]) : Int = {
    var m = ArrayBuffer[Int]();
    for (j <- 0 until a.length){
      if (a(j) == 1){
        m += h(j)
      }
    }

    return m.min;
  }

  def hash1(x : Int) : Int = {
    return (x + 1) % 5
  }

  def hash2(x : Int) : Int = {
    return (3 * x + 1)% 5
  }
}
