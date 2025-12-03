object Day03 extends App {
  

  def largestJoltage(input: String): Int =
    // The output joltage is a 2 digit number.  We should first find the biggest number in anything except the last digit, 
    // then the biggest to the right of that.
    val bank: List[Int] = input.toSeq.map(_.toInt).toList
    val firstBattery = bank.dropRight(1).sorted.last
    val secondBattery = bank.drop(bank.indexOf(firstBattery) + 1).sorted.last
    (firstBattery.toString() + secondBattery.toString()).toInt
}
