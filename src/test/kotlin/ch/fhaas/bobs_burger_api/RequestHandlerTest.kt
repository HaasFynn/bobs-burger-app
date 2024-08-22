package ch.fhaas.bobs_burger_api

import org.junit.jupiter.api.Test
import org.springframework.boot.SpringApplication
import kotlin.test.assertTrue

class RequestHandlerTest {

    @Test
    fun testHomePage() {
        SpringApplication.run(RequestHandler::class.java, *arrayOf())
        getResponses()
        assertTrue(true)
    }

    private fun getResponses() {
        TODO("Not yet implemented")
    }
}