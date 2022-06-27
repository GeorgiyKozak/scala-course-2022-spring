package karazin.scala.users.group.week4.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import java.util.concurrent.Executors
import java.util.UUID
import karazin.scala.users.group.week4.homework.model._
import karazin.scala.users.group.week4.homework.program._


/*
  Write test for all programs in karazin.scala.users.group.week4.homework.program
  Make sure you control custom execution contexts in tests using `before` and `after` logic

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

class ProgramSuite extends munit.FunSuite :

  val sigleThreadContext =
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

  override def munitFixtures = List(sigleThreadContext)

  test("getPostView async test") {
    val userId = UUID.randomUUID()
    val postId = UUID.randomUUID()
    val postView = getPostView(Post(userId, postId))(using sigleThreadContext())
    postView foreach { view =>
      view match
        case PostView(Post(uid, pid), _ :: Nil, _ :: Nil, _ :: Nil) => assertEquals(uid, userId); assertEquals(pid, postId);
        case _ => fail("getPostView failed!")
    }
  }

  test("getPostViews async test") {
    val postViews = getPostsViews()
    for
      list <- postViews
    yield list foreach { view =>
      view match
        case PostView(Post(uid, pid), _ :: Nil, _ :: Nil, _ :: Nil) => assertEquals(uid, UUID.randomUUID()); assertEquals(pid, UUID.randomUUID());
        case _ => fail("getPostViews failed!")
    }
  }





