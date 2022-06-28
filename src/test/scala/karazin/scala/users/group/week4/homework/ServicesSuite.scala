package karazin.scala.users.group.week4.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import java.util.concurrent.Executors
import java.util.UUID
import karazin.scala.users.group.week4.homework.services._
import karazin.scala.users.group.week4.homework.model._

/*
  Write test for all service in karazin.scala.users.group.week4.homework.services
  Make sure you control custom execution contexts in tests using `before` and `after` logic

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

class ServicesSuite extends munit.FunSuite :

  val sigleThreadPoolContext =
    new Fixture[ExecutionContext]("files") {
      var threadpool: ExecutionContextExecutorService = null

      def apply() = threadpool

      override def beforeEach(context: BeforeEach): Unit = {
        threadpool = ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor)
      }

      override def afterEach(context: AfterEach): Unit = {
        // Always gets called, even if test failed.
        threadpool.shutdown()
      }
    }

  override def munitFixtures = List(sigleThreadPoolContext)

  test("getPosts async test") {
    val id = UUID.randomUUID()
    val posts = getPosts(id)(using sigleThreadPoolContext())
    for
      list <- posts
    yield list foreach { post => assertEquals(post.userId, id)
    }
  }

  test("getComments async test") {
    val id = UUID.randomUUID()
    val comments = getComments(id)(using sigleThreadPoolContext())
    for
      list <- comments
    yield list foreach { comment => assertEquals(comment.userId, id) }
  }

  test("getLikes async test") {
    val id = UUID.randomUUID()
    val likes = getLikes(id)(using sigleThreadPoolContext())
    for
      list <- likes
    yield list foreach { like => assertEquals(like.userId, id) }
  }

  test("getShares async test") {
    val id = UUID.randomUUID()
    val shares = getShares(id)(using sigleThreadPoolContext())
    for
      list <- shares
    yield list foreach { share => assertEquals(share.userId, id) }
  }


  