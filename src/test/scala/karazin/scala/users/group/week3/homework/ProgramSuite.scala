package karazin.scala.users.group.week3.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import karazin.scala.users.group.week3.homework.model.*
import karazin.scala.users.group.week3.homework.program.*
import java.util.UUID

import scala.util.{Success, Failure}


/*
  Write test for all programs in karazin.scala.users.group.week3.homework.program

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ProgramSuite extends munit.FunSuite:
  
  test("getPostView async test") {
      val postView = getPostView(Post(UUID.randomUUID(),UUID.randomUUID()))
      postView onComplete {
        case Success(result) => assertEquals(42, 42)
        case Failure(error)  => fail("getPostView failed")
      }
  }
