import advent25.utils.InputFileReader
import scala.collection.mutable.ListBuffer
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

  problems.foreach(p => println(solveCephalopodProblem(p)))

  def solveCephalopodProblem(problem: Vector[String]): Long = 
    // This assumes that the numbers are left-alisgned.  Actually they are 
    // randomly distributed in the input so we need to find their location.
    val inputNumbers = problem.dropRight(1)
    var location = 0;
    val cephlaNumbers = ListBuffer[Int]()

    while (true) {
      val cephlaNumber = inputNumbers.map(s => if location >= s.length then "" else s(location).toString()).reduce((a, b) => a + b)
      println(s"Location $location cephla number $cephlaNumber, inputNumbers $inputNumbers")
      if (!cephlaNumber.isEmpty()) then
        cephlaNumbers.append(cephlaNumber.toInt)
        location += 1
      else
        val result = problem.last match
          case "+" => cephlaNumbers.sum
          case "*" => cephlaNumbers.product
        return result
    }
      
    
    val numbers = problem.dropRight(1).map(_.toLong)
    problem.last match
      case "+" => numbers.sum
      case "*" => numbers.product


  def parseInput(filename: String): Vector[Vector[String]] =
    val lines = InputFileReader(filename).getLines
    lines.map(_.split("\\s+").toVector).toVector
  
}
