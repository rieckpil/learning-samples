package de.rieckpil.learning

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
import org.springframework.context.support.GenericApplicationContext


fun main() {
  val context = GenericApplicationContext()
  XmlBeanDefinitionReader(context).loadBeanDefinitions("services.xml")
  context.refresh()
  val petStore = context.getBean("petStore", PetStore::class.java)
  println(petStore.saySomething())
}
