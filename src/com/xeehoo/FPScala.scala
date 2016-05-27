package com.xeehoo

import scala.annotation.tailrec

/**
 * Created by wangzunhui on 2016/5/20.
 */
object FPScala {
  /**
   * example 2.1
   *
   * @param n
   * @return
   */
  def fib (n : Int) : Int = {
    @tailrec
    def fib2(n: Int, x: Int, y:Int) : Int = n match {
      case 0 => x
      case _ => fib2(n - 1, y, x + y)
    }

    return fib2(n, 0, 1)
  }

  /**
   * example 2.2
   *
   * @param as
   * @param ordered
   * @tparam A
   * @return
   */
  def isSorted[A](as:Array[A], ordered: (A, A) => Boolean) : Boolean = {
    def loop(n : Int) : Boolean = {
      if (n >= as.length - 1) true
      else if (!ordered(as(n), as(n + 1))) false
      else loop(n + 1)
    }

    loop(0)
  }

  def partial1[A,B,C](a: A, f: (A,B) => C): B => C =
    b => f(a, b)

  def curry[A, B, C](f: (A, B) => C) : A => (B => C) = {
    (a : A) => (b : B) => f(a, b)
  }

  def uncurry[A,B,C](f: A => B => C): (A, B) => C =
    (a, b) => f(a)(b)

  def compose[A,B,C](f: B => C, g: A => B): A => C =
    a => f(g(a))

  def main(args: Array[String]) = {
    println("hello scala - " + fib(6))

    val as = Array[Int](1,2,5,4)
    val b = isSorted(as, (x:Int, y:Int) => x < y);
    println("isSorted - " + b);
  }
}
