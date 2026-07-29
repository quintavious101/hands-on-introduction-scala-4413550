import java.io.{File, FileWriter}
import scala.io.Source
import scala.util.{Using, Try}
import Etl.*
import Etl.EtlError.*

object FileUtils:
  def extract(input: String): Either[EtlError, List[String]] =
    try Right(Using.resource(Source.fromFile(input))(_.getLines.toList))
    catch case e: Exception => Left(ExtractError)
  end extract

  def load[A](data: List[A], output: String): Either[EtlError, Unit] =
    Try {
      val file = new File(output)
      val fileWriter = new FileWriter(file)
      fileWriter.write(data.mkString("\n"))
      fileWriter.close()
    }.toEither.left.map(_ => LoadError)
  end load
end FileUtils
