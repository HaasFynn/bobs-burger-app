package ch.fhaas.real_estate_rest_api.entity

class Burger(
    name: String,
    wikiUrl: String,
    val price: Double,
    val season: Season?,
    val episode: Episode?
): Entity(name, wikiUrl)