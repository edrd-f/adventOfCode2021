import util.solve

// Part 1
fun main() = solve(day = 3) { lines ->
  val (lineCount, oneBitCounts) = lines.fold(
    initial = Pair(0, listOf<Int>())
  ) { (lineCount, oneBitCounts), line ->
    val lineBits = line.asIterable().map(Char::digitToInt)
    Pair(
      lineCount + 1,
      if (oneBitCounts.isEmpty()) lineBits
      else oneBitCounts.zip(lineBits, Int::plus)
    )
  }
  val gammaRateBits = oneBitCounts.map { count -> Bit(count > lineCount / 2) }
  gammaRateBits.toInt() * gammaRateBits.map(Bit::flip).toInt()
}

@JvmInline
value class Bit(private val value: Boolean) {
  fun flip() = Bit(!value)
  fun toInt() = if (value) 1 else 0
}

fun List<Bit>.toInt() = foldIndexed(0) { i, int, bit ->
  int + bit.toInt().shl(size - i - 1)
}
