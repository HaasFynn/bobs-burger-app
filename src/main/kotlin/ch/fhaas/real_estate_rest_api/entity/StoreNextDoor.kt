package ch.fhaas.real_estate_rest_api.entity

import ch.fhaas.real_estate_rest_api.entity_properties.Season

data class StoreNextDoor(
    val name: String,
    val firstSeason: Season,
    val firstEpisode: Episode,
    val imageUrl: String,
    val url: String
)