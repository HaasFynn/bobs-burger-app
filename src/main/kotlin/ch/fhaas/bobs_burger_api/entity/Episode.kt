package ch.fhaas.bobs_burger_api.entity


class Episode(
    name: String,
    wikiUrl: String,
    val productionCode: String,
    val airDate: String?,
    val season: Season?,
    val episodeNum: Int,
    val totalViewers: String?,
):Entity(name, wikiUrl)