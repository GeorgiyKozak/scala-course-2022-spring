package karazin.scala.users.group.week3.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import karazin.scala.users.group.week3.homework.model.*
import karazin.scala.users.group.week3.homework.services.*
import java.util.UUID


/*
  Write test for all service in karazin.scala.users.group.week3.homework.services

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ServicesSuite extends munit.FunSuite :

  test("getPosts async test") {
    val id = UUID.randomUUID()
    val posts = getPosts(id)
    for
      list <- posts
    yield list foreach { post => assertEquals(post.userId, id)
    }
  }

  test("getComments async test") {
    val id = UUID.randomUUID()
    val comments = getComments(id)
    for
      list <- comments
    yield list foreach { comment => assertEquals(comment.userId, id) }
  }

  test("getLikes async test") {
    val id = UUID.randomUUID()
    val likes = getLikes(id)
    for
      list <- likes
    yield list foreach { like => assertEquals(like.userId, id) }
  }

  test("getShares async test") {
    val id = UUID.randomUUID()
    val shares = getShares(id)
    for
      list <- shares
    yield list foreach { share => assertEquals(share.userId, id) }
  }







  