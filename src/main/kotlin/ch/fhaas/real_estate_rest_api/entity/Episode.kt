package ch.fhaas.real_estate_rest_api.entity


class Episode(
    name: String,
    wikiUrl: String,
    val productionCode: String,
    val airDate: String?,
    val season: Season?,
    val episodeNum: Int,
    val totalViewers: Int?,
):Entity(name, wikiUrl)