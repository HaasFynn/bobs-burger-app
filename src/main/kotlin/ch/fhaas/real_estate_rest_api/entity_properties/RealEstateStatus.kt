package ch.fhaas.real_estate_rest_api.entity_properties

import jakarta.persistence.Table
import kotlin.random.Random

@Table(name = "status")
enum class RealEstateStatus {
    Sale,
    RENT,
    SOLD;

    fun getRandStatus(): RealEstateStatus {
        return entries.get(Random.nextInt(0, entries.size - 1))
    }
}
