package karazin.scala.users.group.week2.homework

// Do not forget to import custom implementation

import adt._
import model._
import services._

object program:

  /*
   Print all view for all user's posts if they exists
  */

  //implement with flatten to unbox ErrorOr and forEach
  //to print every PostView with print method???????

  def printPostsViews(): ErrorOr[List[PostView]] = ???

  /*
   Getting view for all user's posts if they exists
  */
  def getPostsViews(): ErrorOr[List[ErrorOr[PostView]]] = {
    for
      profile   ← getUserProfile()
      posts     ← getPosts(profile.userId)
      postsView ← ErrorOr(posts map { post ⇒ getPostView(post) })
      if postsView.length == 7
    yield postsView
  }

  /*
   Getting view for a particular user's post
   Provide an argument and a result type
  */
  def getPostView(post: Post): ErrorOr[PostView] = {
    for
      comments <- getComments(post.postId)
      likes    <- getLikes(post.postId)
      shares   <- getShares(post.postId)
    yield PostView(post, comments, likes, shares)
  }

  /*
    Provide desugared version of the previous two methods
  */
  def getPostsViewDesugared(): ErrorOr[List[ErrorOr[PostView]]] =
    getUserProfile() flatMap { profile ⇒
      getPosts(profile.userId)
    } map { posts ⇒
      posts map { post ⇒
        getComments(post.postId) flatMap { comments ⇒
          getLikes(post.postId) flatMap { likes ⇒
            getShares(post.postId) map { shares ⇒
              PostView(post, comments, likes, shares)
            }
          }
        }
      }
    } withFilter { postsView ⇒
      postsView.length == 7
    }


  