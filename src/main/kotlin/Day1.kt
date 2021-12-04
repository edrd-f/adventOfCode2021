import util.solve

fun main() {
  // Part 1
  solve(day = 1) { lines ->
    lines.map(String::toInt)
      .zipWithNext()
      .count { (prev, next) -> next > prev }
  }

  // Part 2
  solve(day = 1) { lines ->
    lines.map(String::toInt)
      .windowed(3) { it.sum() }
      .zipWithNext()
      .count { (prev, next) -> next > prev }
  }
}
