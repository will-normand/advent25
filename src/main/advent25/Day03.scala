package advent25

object Day03 extends App {
  

  def largestJoltage(input: String): Int =
    // The output joltage is a 2 digit number.  We should first find the biggest number in anything except the last digit, 
    // then the biggest to the right of that.
    val bank: List[Int] = input.map(_.toString.toInt).toList
    println("bank is " + bank)
    
    val firstBattery = bank.dropRight(1).sorted.last
    val secondBattery = bank.drop(bank.indexOf(firstBattery) + 1).sorted.last
    println(s"first $firstBattery, secondBattery $secondBattery")
    (firstBattery.toString() + secondBattery.toString()).toInt
}
