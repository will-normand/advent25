package advent25

import advent25.utils.InputFileReader
import scala.annotation.switch
import scala.annotation.tailrec
import scala.collection.immutable.NumericRange.Inclusive

object Day02 extends App {

  val ranges = parseInput(InputFileReader("input02.txt").getLines.head)

  println("Part 1 is " + part1())
  println("Part 2 is " + part2())

  def part1(): Long = ranges.flatMap(range => range.filter(repeatedTwice)).sum
  def part2(): Long = ranges.flatMap(range => range.filter(hasRepeats)).sum

  def parseInput(input: String): List[Inclusive[Long]] =
    input
      .split(",")
      .map(r =>
        r match
          case s"$left-$right" => left.toLong to right.toLong
      )
      .toList

  def repeatedTwice(id: Long): Boolean =
    val idString = id.toString
    if idString.length() % 2 != 0 then return false

    val (x, y) = idString.splitAt(idString.length() / 2)
    x == y

  def hasRepeats(id: Long): Boolean =
    val idString = id.toString

    @tailrec
    def allRepeats(str: String, subseq: String): Boolean =
      if str.isEmpty then return true
      else if str.startsWith(subseq) then
        allRepeats(str.stripPrefix(subseq), subseq)
      else false

    @tailrec
    def repeats0(seq: String): Boolean =
      if seq.length() > (idString.length() / 2) then false
      else if allRepeats(idString, seq) then true
      else repeats0(idString.take(seq.length + 1))

    repeats0(idString.take(1))
  end hasRepeats
}
