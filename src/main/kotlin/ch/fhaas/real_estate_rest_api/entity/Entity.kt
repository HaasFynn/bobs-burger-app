package ch.fhaas.real_estate_rest_api.entity

abstract class Entity protected constructor(
    val name: String = "",
    val wikiUrl: String = ""
)
