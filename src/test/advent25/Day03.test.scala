import advent25.Day03

class Day03Test extends munit.FunSuite {
  test("joltage") {
    assert(Day03.largestJoltage("987654321111111") == 98)
    assert(Day03.largestJoltage("811111111111119") == 89)
    assert(Day03.largestJoltage("234234234234278") == 78)
  }
}
