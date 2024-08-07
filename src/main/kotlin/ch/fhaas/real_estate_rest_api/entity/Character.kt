package ch.fhaas.real_estate_rest_api.entity

import ch.fhaas.real_estate_rest_api.entity_properties.Occupation
import ch.fhaas.real_estate_rest_api.entity_properties.Relatives

data class Character(
    val name: String?,
    val relatives: List<Relatives>?,
    val imageUrl: String?,
    val gender: String?,
    val age: Int?,
    val hair: String?,
    val occupation: Occupation?,
    val allOccupations: List<Occupation>?,
    val firstEpisode: Episode?,
    val voicedBy: String?,
    val wikiUrl: String?,
)