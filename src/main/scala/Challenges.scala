object Challenges {
  /**
   * Write unit tests using ScalaTest to ensure the correctness of this function.
   * Takes a list of numbers and sums them
   */
  def calculateSum(numbers: List[Int]): Int = numbers.sum

  /**
   * Implement the method, filterAndConvert, which takes a list of
   * names, filters out the names that have a length greater than 4
   * characters and converts the remaining names to uppercase.
   */
  def filterAndConvert(names: List[String]): List[String] = 
    val filtered = filter(names)
    convert(filtered)
  end filterAndConvert
}

def filter(names: List[String]): List[String] =
  names.filterNot(_.length > 4)
end filter

def convert(names: List[String]): List[String] = 
  names.map(_.toUpperCase)
end convert