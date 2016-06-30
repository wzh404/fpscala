package com.xeehoo.akka.student

/**
 * Created by wangzunhui on 2016/6/27.
 */
object TeacherProtocol {

  case class QuoteRequest()

  case class QuoteResponse(quoteString: String)

}
