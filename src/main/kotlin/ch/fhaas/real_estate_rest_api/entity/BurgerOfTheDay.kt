package ch.fhaas.real_estate_rest_api.entity

import ch.fhaas.real_estate_rest_api.entity_properties.Season


data class BurgerOfTheDay(
    val name: String,
    val price: Double,
    val season: Season,
    val episode: Episode,
    val wikiUrl: String
)