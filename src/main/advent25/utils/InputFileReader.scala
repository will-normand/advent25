package advent25.utils

import scala.io.Source
import java.io.FileNotFoundException

class InputFileReader(filename: String) {
  def getLines: List[String] = {
    try {
      val source = Source.fromResource(filename)
      val lines = source.getLines().toList
      source.close()
      return lines
    } 
    catch {
      case _: FileNotFoundException => 
        println("FileNotFound: " + filename)
        return List()
    }
  }
}
