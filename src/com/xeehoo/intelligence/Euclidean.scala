package com.xeehoo.intelligence

import scala.collection.Map
/**
 * Created by wangzunhui on 2016/5/30.
 */
class Euclidean {
  def distance(x:Map[String, Double], y:Map[String, Double]) : Double = {
    var sum:Double = 0.0

    x.foreach(e => {
      if (y(e._1) != null){
        sum += Math.pow(e._2 - y(e._1), 2)
      }
    })

    return 1 / ( 1 + Math.sqrt(sum));
  }
}
