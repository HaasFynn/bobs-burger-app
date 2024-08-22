package ch.fhaas.bobs_burger_api

import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class StartAction : ApplicationListener<ApplicationStartedEvent> {

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        setSystemProperties()
    }

    private fun setSystemProperties() {
        val properties = Properties()
        properties["java.awt.headless"] = "false"
        System.setProperties(properties)
    }
}