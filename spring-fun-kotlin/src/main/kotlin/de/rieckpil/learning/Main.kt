package de.rieckpil.learning

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

fun main() {
  val context: ApplicationContext = ClassPathXmlApplicationContext("services.xml")
  val petStore = context.getBean("petStore", PetStore::class.java)
  println(petStore.saySomething())
}
