package com.xeehoo.akka

/**
 * Created by wangzunhui on 2016/6/21.
 */
trait HelloTrait { ref : HelloClass =>
  def a() = {println("aaaa")}
}
