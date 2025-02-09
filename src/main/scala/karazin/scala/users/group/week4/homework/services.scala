
/*
Task:
  • fix the code to make it compilable
  • a client of this code should be able to pass an
    execution context both implicitly and explicitly
  • however neither method should explicetly pass
    an execution context to a future which is used in an implementation:
      • `def myMethod(...): Future[...] = Future { }` is ok
      • `def myMethod(...): Future[...] = Future { }(executionContext)` is not ok 
  • write tests
*/

package karazin.scala.users.group.week4.homework

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success
import scala.util.Failure
import karazin.scala.users.group.week4.homework.model._


object services:

  def getUserProfile()(using ec:ExecutionContext): Future[UserProfile] =
    Future {
      UserProfile(UUID.randomUUID())
    }

  def getPosts(userId: UUID)(using ec:ExecutionContext): Future[List[Post]] =
    Future {
      Post(userId = UUID.randomUUID(), postId = UUID.randomUUID()) :: Nil
    }

  def getComments(postId: UUID)(using ec: ExecutionContext): Future[List[Comment]] =
    Future {
      println(s"Current thread for comments: ${Thread.currentThread().getName}")
      // Emulating time consumed operation
      Thread.sleep(5000)
      Comment(userId = UUID.randomUUID(), postId = UUID.randomUUID()) :: Nil
    }

  def getLikes(postId: UUID)(using ec: ExecutionContext): Future[List[Like]] =
    Future {
      println(s"Current thread for likes: ${Thread.currentThread().getName}")
      // Emulating time consumed operation
      Thread.sleep(2000)
      Like(userId = UUID.randomUUID(), postId = UUID.randomUUID()) :: Nil
    }

  def getShares(postId: UUID)(using ec: ExecutionContext): Future[List[Share]] =
    Future {
      println(s"Current thread for shares: ${Thread.currentThread().getName}")
      // Emulating time consumed operation
      Thread.sleep(500)
      Share(userId = UUID.randomUUID(), postId = UUID.randomUUID()) :: Nil
    }
    
