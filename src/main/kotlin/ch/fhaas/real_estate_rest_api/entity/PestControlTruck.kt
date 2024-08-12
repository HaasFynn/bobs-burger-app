package ch.fhaas.real_estate_rest_api.entity

//Use a Set, reason: First 13 are same
class PestControlTruck(
    name: String,
    wikiUrl: String,
    val season: Season?,
    val episode: Episode?,
    val imageUrl: String
): Entity(name, wikiUrl)
