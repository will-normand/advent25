package advent25

import advent25.utils.InputFileReader
import scala.annotation.switch
import scala.annotation.tailrec

object Day01 extends App {
  println(os.pwd)
  val instructions = getInput("input01.txt")

  val startingDial = 50

  println("Part 1 is " + doRotationPart1(startingDial, instructions, 0))
  println("Part 2 is " + doRotationPart2(startingDial, instructions, 0))

  @tailrec
  def doRotationPart1(dial: Int, instructions: List[String], zeros: Int): Int =
    if instructions.isEmpty then return zeros

    val newDial = rotate(dial, instructions.head)
    if (newDial % 100) == 0 then
      doRotationPart1(newDial, instructions.tail, zeros + 1)
    else doRotationPart1(newDial, instructions.tail, zeros)

  @tailrec
  def doRotationPart2(dial: Int, instructions: List[String], zeros: Int): Int =
    if instructions.isEmpty then return zeros

    val (newDial, newZeros) = rotatePassingZeros(dial, instructions.head)
    doRotationPart2(newDial, instructions.tail, zeros + newZeros)

  def rotate(dial: Int, instruction: String): Int =
    val direction = instruction.head
    val distance = instruction.tail.toInt
    direction match
      case 'L' => dial - distance
      case 'R' => dial + distance

  def rotatePassingZeros(dial: Int, instruction: String): (Int, Int) =
    val direction = instruction.head
    val distance = instruction.tail.toInt
    val dialSum: Int = direction match
      case 'L' => invert(dial) + distance
      case 'R' => dial + distance
    val passesZero = ((dialSum - 1) / 100).abs
    val normalisedDial =
      if (direction == 'L') then invert((dialSum % 100).abs)
      else (dialSum % 100).abs
    (normalisedDial, if normalisedDial == 0 then passesZero + 1 else passesZero)

  def invert(x: Int): Int = (100 - x) % 100

  private def getInput(filename: String) =
    new InputFileReader(filename).getLines
}
