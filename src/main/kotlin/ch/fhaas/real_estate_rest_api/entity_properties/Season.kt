package ch.fhaas.real_estate_rest_api.entity_properties

import ch.fhaas.real_estate_rest_api.entity.Episode

data class Season(
    val seasonNumber: Int?,
    val episode: List<Episode>?
)
