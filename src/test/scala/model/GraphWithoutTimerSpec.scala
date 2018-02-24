package model

import org.scalatest.{Matchers, WordSpec}

class GraphWithoutTimerSpec extends WordSpec with Matchers {
  val graph = Graph.createPetersenGraph

  "A walk" when {
    "valid" should {
      "find the matching lexicographical" in {
        val walkWithLexicographical = Map(
          "ABB" -> "016",
          "ABBECCD" -> "0169723",
          "ABCDE" -> "01234",
          "AA" -> "50",
          "AAAA" -> "5050"
        )

        walkWithLexicographical.map { case (walk, lexicographical) =>
          graph.findLeastLexicographicalWalk(walk) shouldEqual lexicographical
        }
      }
    }

    "invalid" should {
      "return INVALID_LEXICOGRAPHICAL_WALK" in {
        val walks = Set(
          "AABE",
          "ACEBDDB",
          "F",
          "AAF",
          ""
        )

        walks.map { walk =>
          graph.findLeastLexicographicalWalk(walk) shouldEqual Graph.INVALID_LEXICOGRAPHICAL_WALK
        }
      }
    }
  }
}
