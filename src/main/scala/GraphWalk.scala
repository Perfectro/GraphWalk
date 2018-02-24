import model.Graph

object GraphWalk {
  def main(args: Array[String]): Unit = {
    args.length match {
      case 1 if args.head.nonEmpty =>
        val petersenGraph = Graph.createPetersenGraph
        val lexicographical = petersenGraph.findLeastLexicographicalWalk(args.head)
        println(lexicographical)
      case _ =>
        println("Error")
        println("Usage : GraphWalk [input_walk]")
        println("\tinput_walk: A non empty string of letters 'A' to 'E")
    }
  }
}
