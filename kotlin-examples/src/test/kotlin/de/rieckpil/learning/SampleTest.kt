package de.rieckpil.learning

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.assertj.core.api.AbstractFloatAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SampleTest {

  @Nested
  inner class Simple {

    @Test
    fun `test one`() {

      val userClient: UserClient = mockk(relaxed = true)

      every {
        userClient.fetchInformation(any())
      } returns "Duke"

      assertThat(userClient.fetchInformation("Ben")).isEqualTo("Duke")
      assertThat(userClient.fetchInformation("John")).isEqualTo("Duke")
      assertThat(userClient.fetchDetails("Ben")).isEmpty()
      assertThat(3.45f).isCloseTo(3.45f)

      verify {
        userClient.fetchInformation("Ben")
        userClient.fetchInformation("John")
      }

      verifySequence {
        userClient.fetchInformation("Ben")
        userClient.fetchInformation("John")
        userClient.fetchDetails("Ben")
      }
    }

    @Test
    fun `test two`() {

      val expected = TestData(1, "Duke")

      assertThat(TestData(1, "Duke")).isEqualTo(expected)
      assertEquals("h", "H".toLowerCase())
    }
  }

  @Nested
  inner class Complex {

    @Test
    fun `test one`() {
      assertEquals("HELLO", "hello".toUpperCase())
    }

    @Test
    fun `test two`() {
      assertEquals("hello", "HELLO".toLowerCase())
    }
  }
}

data class TestData(private val id: Long, private val name: String)

fun AbstractFloatAssert<*>.isCloseTo(expected: Float) = this.isCloseTo(expected, Offset.offset(0.001f))
