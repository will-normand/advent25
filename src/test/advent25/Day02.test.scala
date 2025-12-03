import advent25.Day02

class Day02Test extends munit.FunSuite {
  test("numbers don't contain repeated sequence") {
    assert(!Day02.repeatedTwice(1))
    assert(!Day02.repeatedTwice(101))
    assert(!Day02.repeatedTwice(998))
    assert(!Day02.repeatedTwice(1188511880))
  }

  test("two digit repeats") {
    assert(Day02.repeatedTwice(11))
    assert(Day02.repeatedTwice(66))
  }

  test("longer repeats") {
    assert(Day02.repeatedTwice(6464))
    assert(Day02.repeatedTwice(123123))
    assert(Day02.repeatedTwice(1188511885))
  }

  test("numbers don't contain general repeated sequence") {
    assert(!Day02.hasRepeats(1))
    assert(!Day02.hasRepeats(101))
    assert(!Day02.hasRepeats(998))
    assert(!Day02.hasRepeats(1188511880))
  }

  test("longer repeats") {
    assert(Day02.hasRepeats(6464))
    assert(Day02.hasRepeats(123123))
    assert(Day02.hasRepeats(1188511885))
  }

  test("multiple repeats") {
    assert(Day02.hasRepeats(123123123))
    assert(Day02.hasRepeats(1212121212))
    assert(Day02.hasRepeats(1111111))
    assert(Day02.hasRepeats(824824824))
  }
}
