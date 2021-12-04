import util.solve

fun parseCommandAmountPair(line: String) = line
  .split(' ')
  .let { (command, amount) -> command to amount.toInt() }


fun partOne() = solve(day = 2) { lines ->
  var horizontalPos = 0L
  var depth = 0L

  lines.forEach { line ->
    val (command, amount) = parseCommandAmountPair(line)
    when (command) {
      "forward" -> horizontalPos += amount
      "up" -> depth -= amount
      "down" -> depth += amount
    }
  }

  horizontalPos * depth
}


fun partTwo() = solve(day = 2) { lines ->
  var horizontalPos = 0L
  var depth = 0L
  var aim = 0L

  lines.forEach { line ->
    val (command, amount) = parseCommandAmountPair(line)
    when (command) {
      "forward" -> {
        horizontalPos += amount
        depth += (aim * amount)
      }
      "up" -> aim -= amount
      "down" -> aim += amount
    }
  }

  horizontalPos * depth
}


fun main() {
  partOne()
  partTwo()
}
