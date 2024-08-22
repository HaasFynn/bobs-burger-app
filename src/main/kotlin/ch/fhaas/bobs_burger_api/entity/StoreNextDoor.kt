package ch.fhaas.bobs_burger_api.entity

class StoreNextDoor(
    name: String,
    wikiUrl: String,
    val firstSeason: Season?,
    val firstEpisode: Episode?,
    val imageUrl: String
): Entity(name, wikiUrl)