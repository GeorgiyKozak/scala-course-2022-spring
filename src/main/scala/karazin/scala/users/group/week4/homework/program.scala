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

  given globalContext: ExecutionContext = ExecutionContext.global

  given singleThreadPoolContext: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor)

  given fixedThreadPoolContext: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))

  // Make sure that the result type is exactly `Future[List[PostView]]`
  // not `Future[List[Future[PostView]]]`
  def getPostsViews(): Future[List[PostView]] = {
    val userProfile = getUserProfile()(using singleThreadPoolContext)
    userProfile onComplete {
      case Success(profile) => {
        getPosts(profile.userId)(using fixedThreadPoolContext) flatMap (posts: List[Post] =>
          posts foldLeft (List[PostView]()) {(acc, el) =>
          getPostView(el)(using globalContext) onComplete {
            case Success (postView: PostView) => postView :: acc
            case Failure (error) => acc
          }
        })(using fixedThreadPoolContext)
      }
      case Failure(error) => error
    }
  }

  def getPostView(post: Post)(using ec: ExecutionContext): Future[PostView] =
    println(s"Main thread: ${Thread.currentThread().getName}")

    val getCommentsService = getComments(post.postId)(using singleThreadPoolContext)
    val getLikesService = getLikes(post.postId)(using fixedThreadPoolContext)
    val getSharesService = getShares(post.postId)(using fixedThreadPoolContext)

    for
      comments ← getCommentsService
      likes ← getLikesService
      shares ← getSharesService
    yield PostView(post, comments, likes, shares)

  end getPostView

  Await.result(getPostView(Post(UUID.randomUUID(), UUID.randomUUID()))(using globalContext), 20 seconds)

  singleThreadPoolContext.shutdown()
  fixedThreadPoolContext.shutdown()
