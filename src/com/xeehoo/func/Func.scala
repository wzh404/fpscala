package com.xeehoo.func

/**
 * Created by wangzunhui on 2016/7/6.
 */
object Func {
  def main(args: Array[String]) = {
    // 规约，对容器中的每两个元素进行一次二元操作
    val numbers = Array(1, 2, 3, 4, 5)
    val sum = numbers.reduceLeft[Int](_ + _)

    println("The sum of the numbers one through five is " + sum)

    // 基于给定的偏函数，遍历容器，得到一个新的容器
    val list = List(1, 4, 9)
    val k = list.collect { case item if item % 2 == 1 => item * 2 }
    println(k)

    //把两个函数组合在一起，形成一个新的函数
    def add2(num : Int) = {num + 2}
    def add4(num : Int) = {num + 4}

    val add = add2 _ compose add4 _
    println(add(3))

    // 折叠容器，给定初始值，以及折叠方法
    println((1 to 10).fold(1)(_ * _))

    // 以指定的条件(结果为key)来对容器中的元素分组，得到一个Map
    println(list.groupBy(_ % 8))

    //按照指定的条件，将容器分成两部分，前一部分满足条件，后半部分不满足
    println(list.partition(_ % 2 == 0))


  }
}
