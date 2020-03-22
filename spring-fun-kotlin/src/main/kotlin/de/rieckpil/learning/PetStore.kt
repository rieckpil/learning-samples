package de.rieckpil.learning

class PetStore(private val remoteService: RemoteService, private val message: String) {

  fun saySomething(): String {
    return remoteService.getData(message)
  }
}
