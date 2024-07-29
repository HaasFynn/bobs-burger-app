package ch.fhaas.real_estate_rest_api.entity_properties

import jakarta.persistence.Table
import kotlin.random.Random

@Table(name = "type")
enum class RealEstateType {
    RESIDENTAL,
    COMMERCIAL,
    LAND;

    fun getRandType(): RealEstateType {
        return entries[Random.nextInt(0, entries.size - 1)]
    }
}
