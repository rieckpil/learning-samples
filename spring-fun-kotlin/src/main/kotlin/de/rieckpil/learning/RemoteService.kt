package de.rieckpil.learning

class RemoteService {

  lateinit var loggingService: LoggingService

  fun getData(message: String): String {
    loggingService.logIt("Access to ${this.javaClass}")
    return "Hello World: $message"

  }
}
