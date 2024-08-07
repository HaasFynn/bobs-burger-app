package ch.fhaas.real_estate_rest_api.entity

import ch.fhaas.real_estate_rest_api.entity_properties.Season


data class Episode(
    val name: String?,
    val productionCode: String?,
    val airDate: String?,
    val season: Season?,
    val episodeNum: Int?,
    val totalViewers: Int?,
    val wikiUrl: String?,
)
