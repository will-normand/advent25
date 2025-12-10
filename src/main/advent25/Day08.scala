import advent25.utils.InputFileReader

object Day08 extends App {
  def parseInput(filename: String) =
    InputFileReader(filename).getLines.map(_ match
      case s"$x,$y,$z" => JunctionBox(x.toInt, y.toInt, z.toInt))

  def closestN(boxes: List[JunctionBox])(n: Int): List[(JunctionBox, JunctionBox)] =
    val boxPairs = for (a <- boxes; b <- boxes if a != b) yield (a, b)
    boxPairs
      .map((a, b) => ((a, b), a.distanceTo(b)))
      .sortBy((_, distance) => distance)
      .map(_._1)
      .take(n)

  val boxes = parseInput("input08.txt")
  println(closestN(boxes)(1))
}

case class JunctionBox(x: Int, y: Int, z: Int) {
  def distanceTo(other: JunctionBox): Double =
    Math.sqrt(
      Math.pow((x - other.x), 2)
        + Math.pow((y - other.y), 2)
        + Math.pow((z - other.z), 2)
    )
}

case class Circuit(boxes: List[JunctionBox])