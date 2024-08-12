package ch.fhaas.real_estate_rest_api.entity

class StoreNextDoor(
    name: String,
    wikiUrl: String,
    val firstSeason: Season?,
    val firstEpisode: Episode?,
    val imageUrl: String
): Entity(name, wikiUrl)