package de.rieckpil.learning

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DiamondTest {

  private val inputsAfterA = 'B'..'Z'

  @Test
  fun `just an A`() {
    assertEquals("A", diamond('A'))
  }

  @Test
  fun `number of lines`() {
    inputsAfterA.forEach { c ->
      val s = diamond(c).lines()
      assertEquals(order(c) * 2 + 1, s.size)
    }
  }

  @Test
  fun `each line has a distinct character`() {
    inputsAfterA.forEach { c ->
      val lines = diamond(c).lines()
      lines
        .take(lines.size / 2)
        .forEachIndexed { lineIndex, line ->
          val expectedChar = 'A' + lineIndex
          assertEquals(setOf(expectedChar, ' '), line.toSet(), "line $lineIndex contains $expectedChar and whitespace")
        }
    }
  }

  @Test
  fun `top-left quadrant is single character indented`() {
    inputsAfterA.forEach { c ->
      val lines = diamond(c).lines()
      val top = lines.take((lines.size + 1) / 2)
      val topLeft = top.map { it.take((it.length + 1) / 2) }
      topLeft
        .forEachIndexed { lineIndex, line ->
          val expectedChar = 'A' + lineIndex
          assertEquals(setOf(expectedChar, ' '), line.toSet(), "line $lineIndex contains $expectedChar and whitespace")
        }
    }
  }
}
