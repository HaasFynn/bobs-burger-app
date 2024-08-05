package ch.fhaas.real_estate_rest_api.entity_properties

import jakarta.persistence.Table
import kotlin.random.Random

@Table(name = "status")
enum class Status {
    SALE,
    RENT,
    SOLD,
    UNDEFINED;

    fun getRandStatus(): Status {
        return entries[Random.nextInt(0, entries.size - 1)]
    }
}
