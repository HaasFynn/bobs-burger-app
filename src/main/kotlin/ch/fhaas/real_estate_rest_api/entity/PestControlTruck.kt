package ch.fhaas.real_estate_rest_api.entity

import ch.fhaas.real_estate_rest_api.entity_properties.Season

//Use a Set, reason: First 13 are same
data class PestControlTruck(
    val name: String,
    val season: Season,
    val episode: Episode,
    val imageUrl: String,
    val url: String
)
