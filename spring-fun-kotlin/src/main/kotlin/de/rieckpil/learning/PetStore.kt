package de.rieckpil.learning

class PetStore() {

  lateinit var remoteService: RemoteService

  fun saySomething(): String {
    return remoteService.getData()
  }
}
