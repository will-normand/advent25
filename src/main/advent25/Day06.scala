import advent25.utils.InputFileReader
object Day06 extends App {
  
  val inputMatrix = parseInput("input06.txt")
  val problems = inputMatrix.transpose

  def part1() = problems.map(solveProblem).sum
  println("Part 1 is " + part1())

  def solveProblem(problem: Vector[String]): Long = 
    val numbers = problem.dropRight(1).map(_.toLong)
    problem.last match
      case "+" => numbers.sum
      case "*" => numbers.product

  def parseInput(filename: String): Vector[Vector[String]] =
    val lines = InputFileReader(filename).getLines
    lines.map(_.split("\\s+").toVector).toVector
  
}
