package karazin.scala.users.group.week3.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import karazin.scala.users.group.week3.homework.model.*
import karazin.scala.users.group.week3.homework.program.*
import java.util.UUID


/*
  Write test for all programs in karazin.scala.users.group.week3.homework.program

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ProgramSuite extends munit.FunSuite :

  test("getPostView async test") {
    val userId = UUID.randomUUID()
    val postId = UUID.randomUUID()
    val postView = getPostView(Post(userId, postId))
    postView foreach { view =>
      view match
        case PostView(Post(uid, pid), _ :: Nil, _ :: Nil, _ :: Nil) => assertEquals(uid, userId); assertEquals(pid, postId);
        case _                                                      => fail("getPostView failed!")
    }
  }
