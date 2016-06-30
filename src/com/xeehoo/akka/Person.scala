package com.xeehoo.akka

/**
 * Created by wangzunhui on 2016/6/21.
 */
class Person(val name : String, val age: Int) {
  def +(num: Int) = age + num
  def +(p: Person) = age + p.age
}
