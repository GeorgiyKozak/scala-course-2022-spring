package karazin.scala.users.group.week5.homework

import karazin.scala.users.group.week5.homework.givens.JsonStringEncoder

object givens:

  /* 
    The trait is used for converting instances to a json string representation
    Provide a type parameter(s) for the trait and the method 
    and argument(s) and a result type
  */

  trait JsonStringEncoder[T]:
    def encode(v: T): String

  given IntEncoder: JsonStringEncoder[Int] with
    def encode(v: Int): String = v.toString

  given BooleanEncoder: JsonStringEncoder[Boolean] with
    def encode(v: Boolean) = v.toString

  given StringEncoder: JsonStringEncoder[String] with
    def encode(v: String): String = "\"" + v + "\""

  given ListEncoder[T] (using encoder: => JsonStringEncoder[T]): JsonStringEncoder[List[T]] with
    def encode(v: List[T]): String = v.map(el => encoder.encode(el)).mkString("[",", ","]")

  object JsonStringEncoder:
    def apply[T](using encoder: JsonStringEncoder[T]): JsonStringEncoder[T] =
      encoder
/*
  Make sure that integers, booleans, strings and lists
  are convertable to a json string representation
*/