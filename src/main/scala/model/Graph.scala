package model

import scala.annotation.tailrec

case class Graph(vertices: Map[Vertex, Set[Vertex]]) {
  def findLeastLexicographicalWalk(walk: String): String = {
    if (walk.isEmpty) {
      Graph.INVALID_LEXICOGRAPHICAL_WALK
    } else {
      findVertices(walk.head.toString)
        .map(vertexCursor => findLeastLexicographicalWalk(walk, 1, vertexCursor, ""))
        .filter(_.isDefined) match {
        case lexicographicalWalks if lexicographicalWalks.isEmpty =>
          Graph.INVALID_LEXICOGRAPHICAL_WALK
        case lexicographicalWalks =>
          lexicographicalWalks
            .min
            .getOrElse(Graph.INVALID_LEXICOGRAPHICAL_WALK)
      }
    }
  }

  private def findVertices(name: String): Set[Vertex] = {
    vertices
      .filter(vertexWithLink => vertexWithLink._1.name == name)
      .keys
      .toSet
  }

  @tailrec
  private def findLeastLexicographicalWalk(walk: String, walkCursor: Int, vertexCursor: Vertex, lexicographical: String): Option[String] = {
    if (walkCursor >= walk.length) {
      Option(lexicographical + vertexCursor.index.toString)
    } else {
      vertices(vertexCursor)
        .find(vertex => vertex.name == walk.charAt(walkCursor).toString) match {
        case None =>
          None
        case Some(vertex) =>
          findLeastLexicographicalWalk(walk, walkCursor + 1, vertex, lexicographical + vertexCursor.index.toString)
      }
    }
  }
}

object Graph {
  val INVALID_LEXICOGRAPHICAL_WALK = "-1"

  def createPetersenGraph: Graph = {
    Graph(
      vertices = Map(
        Vertex(0, "A") -> Set(Vertex(1, "B"), Vertex(4, "E"), Vertex(5, "A")),
        Vertex(1, "B") -> Set(Vertex(0, "A"), Vertex(2, "C"), Vertex(6, "B")),
        Vertex(2, "C") -> Set(Vertex(1, "B"), Vertex(3, "D"), Vertex(7, "C")),
        Vertex(3, "D") -> Set(Vertex(2, "C"), Vertex(4, "E"), Vertex(8, "D")),
        Vertex(4, "E") -> Set(Vertex(0, "A"), Vertex(3, "D"), Vertex(9, "E")),
        Vertex(5, "A") -> Set(Vertex(0, "A"), Vertex(7, "C"), Vertex(8, "D")),
        Vertex(6, "B") -> Set(Vertex(1, "B"), Vertex(8, "D"), Vertex(9, "E")),
        Vertex(7, "C") -> Set(Vertex(2, "C"), Vertex(5, "A"), Vertex(9, "E")),
        Vertex(8, "D") -> Set(Vertex(3, "D"), Vertex(5, "A"), Vertex(6, "B")),
        Vertex(9, "E") -> Set(Vertex(4, "D"), Vertex(6, "B"), Vertex(7, "C"))
      )
    )
  }
}
