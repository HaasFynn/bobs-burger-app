package ch.fhaas.bobs_burger_api.entity

class Burger(
    name: String,
    wikiUrl: String,
    val price: String,
    val season: Season?,
    val episode: Episode?
): Entity(name, wikiUrl)