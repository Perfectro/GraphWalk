package model

import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.time.{Millis, Span}
import org.scalatest.{Matchers, WordSpec}

class GraphWithTimerSpec extends WordSpec with Matchers with TimeLimitedTests {
  val timeLimit = Span(300, Millis)
  val graph = Graph.createPetersenGraph

  "A walk" when {
    "valid and longer than 10.000 vertices" should {
      val walk = buildStringFromPattern("ABCDE", numberOfRepetition = 2000)
      val lexicographical = buildStringFromPattern("01234", numberOfRepetition = 2000)

      "be find in less than 300 milliseconds" in {
        graph.findLeastLexicographicalWalk(walk) shouldEqual lexicographical
      }
    }

    "invalid and longer than 10.001 vertices" should {
      val walk = buildStringFromPattern("ABCDE", numberOfRepetition = 2000) + "F"

      "return INVALID_LEXICOGRAPHICAL_WALK in less than 300 milliseconds" in {
        graph.findLeastLexicographicalWalk(walk) shouldEqual Graph.INVALID_LEXICOGRAPHICAL_WALK
      }
    }
  }

  private def buildStringFromPattern(pattern: String, numberOfRepetition: Int): String = {
    List
      .range(0, numberOfRepetition)
      .foldLeft("")((accumulator, element) => accumulator + pattern)
  }
}
