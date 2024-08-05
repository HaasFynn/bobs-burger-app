package ch.fhaas.real_estate_rest_api

import org.junit.jupiter.api.Test
import org.springframework.boot.SpringApplication

class RequestHandlerTest {

    @Test
    fun testHomePage() {
        SpringApplication.run(RequestHandler::class.java, *arrayOf())
    }
}