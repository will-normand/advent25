package advent25

import advent25.utils.InputFileReader

object Day03 extends App {

  val banks = InputFileReader("input03.txt").getLines

  def part1(): Int = banks.map(largestJoltage).sum
  println("Part 1 is " + part1())

  def largestJoltage(input: String): Int =
    // The output joltage is a 2 digit number.  We should first find the
    // biggest number in anything except the last digit,
    // then the biggest to the right of that.
    val bank: List[Int] = input.map(_.toString.toInt).toList

    val firstBattery = bank.dropRight(1).sorted.last
    val secondBattery = bank.drop(bank.indexOf(firstBattery) + 1).sorted.last
    (firstBattery.toString() + secondBattery.toString()).toInt
}
