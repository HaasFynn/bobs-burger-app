package ch.fhaas.real_estate_rest_api.entity_properties

import kotlin.random.Random

enum class Type {
    RESIDENTIAL,
    COMMERCIAL,
    LAND,
    UNDEFINED;

    fun getRandType(): Type {
        return entries[Random.nextInt(0, entries.size - 1)]
    }
}
