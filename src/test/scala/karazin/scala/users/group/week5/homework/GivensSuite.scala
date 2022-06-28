package karazin.scala.users.group.week5.homework

import scala.concurrent.Future
import karazin.scala.users.group.week5.homework.givens.{given, _}

/*
  Write test for all programs in karazin.scala.users.group.week4.homework.givens

  Make sure that the following cases are tested:
    • json string representation for integers works
    • json string representation for booleans works
    • json string representation for strings works
    • json string representation for lists for integers, booleans and strings works
    • json string representation for maps fails on compile time

  Review:
    • https://www.json.org/json-en.html
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/assertions.html#compileerrors
    
  NB: Do not use sync, this homework does not belong async stuff
    
 */
class GivensSuite extends munit.FunSuite :

  test("Json Int positive") {
    assertEquals(JsonStringEncoder[Int].encode(19), "19")
  }

  test("Json Int negative") {
    assertEquals(JsonStringEncoder[Int].encode(-100), "-100")
  }

  test("Json Int zero") {
    assertEquals(JsonStringEncoder[Int].encode(0), "0")
  }

  test("Json Boolean false") {
    assertEquals(JsonStringEncoder[Boolean].encode(false), "false")
  }

  test("Json Boolean true") {
    assertEquals(JsonStringEncoder[Boolean].encode(true), "true")
  }

  test("Json String") {
    assertEquals(JsonStringEncoder[String].encode("Success is the ability to go from failure to failure without losing your enthusiasm."),
      "\"Success is the ability to go from failure to failure without losing your enthusiasm.\"")
  }

  test("Json Empty String") {
    assertEquals(JsonStringEncoder[String].encode(""), "\"\"")
  }

  test("Json List Int") {
    assertEquals(JsonStringEncoder[List[Int]].encode(0 :: 25 :: -11 :: Nil), "[0, 25, -11]")
  }

  test("Json List Boolean") {
    assertEquals(JsonStringEncoder[List[Boolean]].encode(true :: false :: Nil), "[true, false]")
  }

  test("Json List String") {
    assertEquals(JsonStringEncoder[List[String]].encode("Smells" :: "like" :: "teen" :: "spirit" :: Nil),
      "[\"Smells\", \"like\", \"teen\", \"spirit\"]")
  }

  test("Json Empty List") {
    assertEquals(JsonStringEncoder[List[String]].encode(Nil), "[]")
  }

  test("Json Map") {
    compileErrors(JsonStringEncoder[Map[String, Int]].encode(Map("uno" -> 1, "dos" -> 2, "tres" -> 3)))
  }



