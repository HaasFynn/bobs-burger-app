package ch.fhaas.bobs_burger_api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Main

fun main(args: Array<String>) {
    SpringApplication.run(RequestHandler::class.java, * args)
    openUrl("http://localhost:8080")
}
