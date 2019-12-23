package de.rieckpil.learning

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import kotlinx.serialization.protobuf.ProtoBuf

fun main() {
  println("Hello World")

  val json = Json(JsonConfiguration.Stable)
  val personOne = Person("Philip", 1337)
  val jsonData = json.stringify(Person.serializer(), personOne)
  val jsonList = json.stringify(Person.serializer().list, listOf(Person("Philip", 1337), Person("Mike", 42, "MK")))


  println(jsonData)
  println(jsonList)

  println(ProtoBuf.dump(Person.serializer(), personOne))

  val person = json.parse(Person.serializer(), """{"name":"John","id":1}""")
  println(person)
}


@Serializable
data class Person(val name: String, val id: Long, val nickname: String = "duke")
