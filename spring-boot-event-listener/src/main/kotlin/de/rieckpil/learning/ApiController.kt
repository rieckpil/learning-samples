package de.rieckpil.learning

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ThreadLocalRandom

@RestController
class ApiController(val applicationEventPublisher: ApplicationEventPublisher) {

  @GetMapping("/api")
  @Transactional
  fun triggerEvent(): String {

    applicationEventPublisher.publishEvent(InvoiceEvent(this, "Hello World"))

    println("---------------------")
    println("Returning API message")

    return "Hello World"
  }
}

@Component
class InvoiceEventListener {

  @EventListener
  fun listen(invoiceEvent: InvoiceEvent) {
    println("invoiceEvent- ${Thread.currentThread().name}")
  }

  @Async
  @EventListener
  fun listenAsync(invoiceEvent: InvoiceEvent) {
    println("Listen async $invoiceEvent - ${Thread.currentThread().name}")

    if (ThreadLocalRandom.current().nextBoolean()) {
      throw RuntimeException("Error")
    }
  }

  @TransactionalEventListener
  fun listenToTransaction(invoiceEvent: InvoiceEvent) {
    println("Transaction finished successfully $invoiceEvent - ${Thread.currentThread().name}")
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
  fun listenToTransactionFailure(invoiceEvent: InvoiceEvent) {
    println("Transaction failed $invoiceEvent")
  }

}

class InvoiceEvent(source: Any, val message: String) : ApplicationEvent(source)
