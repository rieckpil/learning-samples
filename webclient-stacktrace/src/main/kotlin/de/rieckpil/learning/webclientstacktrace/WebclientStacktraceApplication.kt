package de.rieckpil.learning.webclientstacktrace

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebclientStacktraceApplication

fun main(args: Array<String>) {
	runApplication<WebclientStacktraceApplication>(*args)
}
