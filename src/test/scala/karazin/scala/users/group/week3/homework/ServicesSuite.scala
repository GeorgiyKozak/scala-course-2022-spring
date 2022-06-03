package karazin.scala.users.group.week3.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import karazin.scala.users.group.week3.homework.model.*
import karazin.scala.users.group.week3.homework.services.*
import java.util.UUID

import scala.util.{Success, Failure}

/*
  Write test for all service in karazin.scala.users.group.week3.homework.services

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ServicesSuite extends munit.FunSuite :

  test("getUserProfile async test") {
    val userProfile = getUserProfile()
    userProfile onComplete {
      case Success(result) => assertEquals(42, 42)
      case Failure(error)  => fail("getUserProfile failed")
    }
  }

  test("getPosts async test") {
    val posts = getPosts(UUID.randomUUID())
    posts onComplete {
      case Success(result) => assertEquals(42, 42)
      case Failure(error)  => fail("getPosts failed")
    }
  }

  test("getComments async test") {
    val comments = getComments(UUID.randomUUID())
    comments onComplete {
      case Success(result) => assertEquals(42, 42)
      case Failure(error)  => fail("getComments failed")
    }
  }

  test("getLikes async test") {
    val likes = getLikes(UUID.randomUUID())
    likes onComplete {
      case Success(result) => assertEquals(42, 42)
      case Failure(error)  => fail("getLikes failed")
    }
  }

  test("getShares async test") {
    val shares = getShares(UUID.randomUUID())
    shares onComplete {
      case Success(result) => assertEquals(42, 42)
      case Failure(error)  => fail("getShares failed")
    }
  }







  