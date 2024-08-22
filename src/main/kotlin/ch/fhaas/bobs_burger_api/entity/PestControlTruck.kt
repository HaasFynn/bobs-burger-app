package ch.fhaas.bobs_burger_api.entity

class PestControlTruck(
    name: String,
    wikiUrl: String,
    val season: Season?,
    val episode: Episode?,
    val imageUrl: String
): Entity(name, wikiUrl)
