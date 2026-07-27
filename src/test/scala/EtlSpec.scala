// Importing Utilities and Other Things
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import scala.util.{Try, Using}
import scala.io.Source

/* EtlSpec extends AnyFreeSpec which is a testing style, with Matchers (different domain specific language (DSL)) 
so that we can use "should" instead of "assert". It's goal is to run tests (etl) with the test name in quotes.

What we're testing is etl (extract, transform, load) using the vals input, output, and expectedFileContents*/

class EtlSpec extends AnyFreeSpec with Matchers {
  "etl" - {
    "Transform a text file by making all the text lowercase and saving this to a new file" in {
      val input = "/workspaces/hands-on-introduction-scala-4413550/src/test/scala/resources/input.txt"
      val output = "/workspaces/hands-on-introduction-scala-4413550/src/test/scala/resources/output.txt"
      val expectedFileContents = List("hello world")
      etl(input, output)
      readFile(output) shouldEqual Success(expectedFileContents)
    }
  }
  // This is the test assertion, which will need to read the output file and check its contents
  /* This is a private method for reading the file, so it'll take a filePath and return a Try of
  list of string.
  Try will either return an exception or a successfully computed value*/
  /* Source provides methods to create a representation of a file
    fromFile(filePath): BufferedSource
    BufferedSource creates an iterable rep of a source file
    getLines(): Iterator[String] (a collection) */

  private def readFile(filePath: String): Try[List[String]] = 
    // Source.fromFile(filePath) is reading the file
    // _.getLines is getting the lines of our file
    // toList is turning the iterable into a list
    // Using is making sure we close our resource safely, which returns a Try (Failure or Success)
    Using(Source.fromFile(filePath))(_.getLines.toList)
}