package karazin.scala.users.group.week1.homework

/* 
  Custom implementation of Option (Maybe monad in Haskell)
  Implemented via Scala 3 way for Algebraic Data Types (ADT)
  
  Resources:
  * https://en.wikipedia.org/wiki/Algebraic_data_type
  * https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
*/

object adt:

  enum ErrorOr[+V]:
    /* 
      Two case must be defined: 
      * a case for a regular value
      * a case for an error (it should contain an actual throwable)
     */
    case Some(x: V) extends ErrorOr[V]

    case Error(ex: Throwable) extends ErrorOr[V]

    /* 
      The method is used for defining execution pipelines
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
    */
    def flatMap[Q](f: V => ErrorOr[Q]): ErrorOr[Q] =
      this match
        case ErrorOr.Error(ex) => ErrorOr.Error(ex)
        case ErrorOr.Some(v)   => try f(v) catch {
          case e: Throwable => ErrorOr.Error(e)
        }


    /* 
      The method is used for changing the internal object
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
     */
    def map[Q](f: V => Q): ErrorOr[Q] =
      this match
        case ErrorOr.Error(ex) => ErrorOr.Error(ex)
        case ErrorOr.Some(v)   => try ErrorOr.Some(f(v)) catch {
          case e: Throwable => ErrorOr.Error(e)
        }

  // Companion object to define constructor
  object ErrorOr:
    /* 
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
    */
    def apply[V](v: V): ErrorOr[V] =
      if v == null then ErrorOr.Error(NullPointerException("argument's value is null!")) else ErrorOr.Some(v)
      
  