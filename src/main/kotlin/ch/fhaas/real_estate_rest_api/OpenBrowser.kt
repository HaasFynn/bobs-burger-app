package ch.fhaas.real_estate_rest_api

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.awt.Desktop
import java.net.URI

@Component
class OpenBrowser : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        openHomePage()
    }

    private fun openHomePage() {
        val uri: URI = URI.create("http://localhost:8080")
        try {
            Desktop.getDesktop().browse(uri)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}