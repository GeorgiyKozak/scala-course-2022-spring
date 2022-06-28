/*
Task:
  • fix the code to make it compilable
  • use at least 2 execution contexts:
    • one for a `for comprehension`
    • at least one for `getComments`, `getLikes`, `getShares`
  • write tests
*/

package karazin.scala.users.group.week4.homework

import java.util.UUID
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutorService, Future}
import scala.concurrent.duration.DurationInt
import scala.util.Success
import scala.util.Failure
import java.util.concurrent.Executors
import karazin.scala.users.group.week4.homework.model.*
import karazin.scala.users.group.week4.homework.services.*

import scala.language.postfixOps

object program extends App :

  given ExecutionContext = ExecutionContext.global

  // Make sure that the result type is exactly `Future[List[PostView]]`
  // not `Future[List[Future[PostView]]]`
  def getPostsViews(): Future[List[PostView]] = {
    val singleThreadPoolContext: ExecutionContextExecutorService =
      ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor)

    val fixedThreadPoolContext: ExecutionContextExecutorService =
      ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))

    val userProfile = getUserProfile()(using singleThreadPoolContext)
    for
      profile   <- userProfile
      posts     <- getPosts(profile.userId)(using fixedThreadPoolContext)
      postsView = posts map { post => getPostView(post) }
      postViews <- Future sequence {
        postsView
      } //from List[Future[PostView]] to Future[List[PostView]]
    yield {
      singleThreadPoolContext.shutdown()
      fixedThreadPoolContext.shutdown()
      postViews
    }
  }

  def getPostView(post: Post)(using ec: ExecutionContext): Future[PostView] =
    println(s"Main thread: ${Thread.currentThread().getName}")

    val singleThreadPoolContext: ExecutionContextExecutorService =
      ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor)

    val fixedThreadPoolContext: ExecutionContextExecutorService =
      ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))

    val getCommentsService = getComments(post.postId)(using singleThreadPoolContext)
    val getLikesService    = getLikes(post.postId)(using fixedThreadPoolContext)
    val getSharesService   = getShares(post.postId)(using fixedThreadPoolContext)

    for
      comments ← getCommentsService
      likes    ← getLikesService
      shares   ← getSharesService
    yield {
      singleThreadPoolContext.shutdown()
      fixedThreadPoolContext.shutdown()
      PostView(post, comments, likes, shares)
    }

  end getPostView

//singleThreadPoolContext.shutdown()
//fixedThreadPoolContext.shutdown()
