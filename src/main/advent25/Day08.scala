import advent25.utils.InputFileReader
import scala.annotation.tailrec

object Day08 extends App {
  def parseInput(filename: String) =
    InputFileReader(filename).getLines.map(_ match
      case s"$x,$y,$z" => JunctionBox(x.toInt, y.toInt, z.toInt))

  def closest(boxes: List[JunctionBox]): List[(JunctionBox, JunctionBox)] =
    val boxPairs = boxes.combinations(2).map(item => (item(0), item(1))).toList
    boxPairs
      .map((a, b) => ((a, b), a.distanceTo(b)))
      .sortBy((_, distance) => distance)
      .map(_._1)

  val boxes = parseInput("input08.txt")
  val circuits = boxes.map(box => Circuit(Set(box)))

  def part1() =
    val connections = connect(circuits.toSet, closest(boxes).take(1000))
    println(s"We have ${connections.size} circuits")
    val top3 = connections.toList.sortBy(_.boxes.size).reverse.take(3)
    top3.map(_.boxes.size).product

  def part2() =
    val connections = connect(circuits.toSet, closest(boxes))
    val top3 = connections.toList.sortBy(_.boxes.size).reverse.take(3)

  println("Part 1 is " + part1())
  println("Part 2 is " + part2())

  @tailrec
  def connect(
      circuits: Set[Circuit],
      closestList: List[(JunctionBox, JunctionBox)]
  ): Set[Circuit] =
    if closestList.isEmpty || circuits.size == 1 then return circuits
    else
      val closest = closestList.head
      val firstCircuit = circuits.filter(_.boxes.contains(closest._1)).head
      val secondCircuit = circuits.filter(_.boxes.contains(closest._2)).head
      val combinedCircuit = firstCircuit.connect(secondCircuit)
      val newCircuits =
        ((circuits - firstCircuit) - secondCircuit) + combinedCircuit

      if newCircuits.size == 1 then
        // Reached the end of part 2.  Cheat a bit and just print out the answer.
        println(
          s"Part 2: last pair is $closest, distance from wall is ${closest._1.x * closest._2.x}"
        )

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
