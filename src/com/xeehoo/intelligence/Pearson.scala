package com.xeehoo.intelligence

import scala.collection.Map

/**
 * Created by wangzunhui on 2016/5/30.
 */
class Pearson {
  def distance(x:Map[String, Double], y:Map[String, Double]) : Double = {
    var r:Double = 0.0;

    val sumx = x.foldLeft(0.0)((a, p) => a + p._2)
    val sumy = y.foldLeft(0.0)(_+_._2)

    val sumxsq = x.foldLeft(0.0)((a, p) => a + Math.pow(p._2, 2))
    val sumysq = y.foldLeft(0.0)((a, p) => a + Math.pow(p._2, 2))

    var psum = 0.0;
    for ((k, v) <- x){
      psum += y(k) * v;
    }

    val n = x.size;
    val sum = psum - (sumx * sumy / n)
    val den = Math.sqrt((sumxsq - Math.pow(sumx, 2) / n) * (sumysq - Math.pow(sumy, 2) / n))

    if (den == 0.0) return 0

    return sum / den;
  }
}
