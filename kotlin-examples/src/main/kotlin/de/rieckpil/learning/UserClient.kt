package de.rieckpil.learning

import java.util.*
import kotlin.LazyThreadSafetyMode.NONE

class UserClient {
  fun fetchInformation(username: String) = UUID.randomUUID().toString()

  @Deprecated("use fetchInformation instead",
    replaceWith = ReplaceWith("this.fetchInformation(username)"))
  fun fetchDetails(username: String): String {
    require(username.isNotEmpty()) { "can't work with empty username" }

    val usernameCapitalized: String by lazy(NONE) {
      println("initialized")
      username.toUpperCase()
    }

    usernameCapitalized.toString()

    return "Details about $username"
  }

  fun getLocale(tags: Map<Locale, List<String>> = mapOf(
    Locale.GERMANY to listOf("DE"),
    Locale.ENGLISH to listOf("EN")
  )) = tags

  @JvmName("sortStrings")
  fun sort(strings: List<String>) = "Duke"

  @JvmName("sortInts")
  fun sort(ints: List<Int>) = "Duke"
}
