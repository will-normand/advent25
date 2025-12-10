import advent25.utils.InputFileReader

object Day08 extends App {
  def parseInput(filename: String) =
    InputFileReader(filename).getLines.map(_ match
      case s"$x,$y,$z" => JunctionBox(x.toInt, y.toInt, z.toInt))

  def closestN(boxes: List[JunctionBox])(
      n: Int
  ): List[(JunctionBox, JunctionBox)] =
    val boxPairs = boxes.combinations(2).map(item => (item(0), item(1))).toList
    boxPairs
      .map((a, b) => ((a, b), a.distanceTo(b)))
      .sortBy((_, distance) => distance)
      .map(_._1)
      .take(n)

  val boxes = parseInput("input08.txt")
  val circuits = boxes.map(box => Circuit(Set(box)))

  def part1() =
    val connections = connect(circuits.toSet, closestN(boxes)(1000))
    println(s"We have ${connections.size} circuits")
    // connections.foreach(println(_))
    val top3 = connections.toList.sortBy(_.boxes.size).reverse.take(3)
    // top3.foreach(println)
    top3.map(_.boxes.size).product

  println("Part 1 is " + part1())

  def connect(
      circuits: Set[Circuit],
      closestList: List[(JunctionBox, JunctionBox)]
  ): Set[Circuit] =
    if closestList.isEmpty then return circuits
    else
      val closest = closestList.head
      val firstCircuit = circuits.filter(_.boxes.contains(closest._1)).head
      val secondCircuit = circuits.filter(_.boxes.contains(closest._2)).head
      val combinedCircuit = firstCircuit.connect(secondCircuit)
      println(
        s"CLOSEST PAIR $closest, first $firstCircuit, second $secondCircuit, combined $combinedCircuit"
      )
      val newCircuits =
        ((circuits - firstCircuit) - secondCircuit) + combinedCircuit
      connect(newCircuits, closestList.tail)
}

case class JunctionBox(x: Int, y: Int, z: Int) {
  def distanceTo(other: JunctionBox): Double =
    Math.sqrt(
      Math.pow((x - other.x), 2)
        + Math.pow((y - other.y), 2)
        + Math.pow((z - other.z), 2)
    )
}

case class Circuit(boxes: Set[JunctionBox]) {
  def connect(other: Circuit) =
    Circuit(boxes ++ other.boxes)
}
