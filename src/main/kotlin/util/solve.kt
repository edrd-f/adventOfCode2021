package util

import java.io.InputStreamReader

fun solve(day: Int, execute: (Sequence<String>) -> Any) {
  loadInput(day).useLines { lines ->
    execute(lines.filter(String::isNotBlank))
  }.let(::println)
}

private fun loadInput(day: Int): InputStreamReader = {}::class.java
  .classLoader
  .getResourceAsStream("inputs/day$day")!!
  .reader()
