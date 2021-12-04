import util.solve

fun main() {
  /*
   * Part 1.
   * This implementation, although lengthy, can take any input
   * size while keeping low memory footprint and good performance
   * given the input is scanned only once and computations are lazy.
   */
  solve(day = 3) { lines ->
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
  /*
   * Part 2
   */
  solve(day = 3) { lines ->
    fun filterBits(report: List<List<Int>>, invert: Boolean): List<Int> =
      report.first().indices.fold(report) { reportFiltered, column ->
        val (ones, zeroes) = reportFiltered.partition { it[column] == 1 }
        (if ((ones.size >= zeroes.size) xor invert) ones else zeroes)
          .also { if (it.size == 1) return@filterBits it[0] }
      }.first()

    val reportLines = lines
      .map { line -> line.asIterable().map(Char::digitToInt) }
      .toList()

    filterBits(reportLines, false).joinToString("").toInt(2)
      .times(filterBits(reportLines, true).joinToString("").toInt(2))
  }
}

@JvmInline
value class Bit(private val value: Boolean) {
  fun flip() = Bit(!value)
  fun toInt() = if (value) 1 else 0
}

fun List<Bit>.toInt() = foldIndexed(0) { i, int, bit ->
  int + bit.toInt().shl(size - i - 1)
}
