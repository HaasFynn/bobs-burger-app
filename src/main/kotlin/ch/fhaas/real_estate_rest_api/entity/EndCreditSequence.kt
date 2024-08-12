package ch.fhaas.real_estate_rest_api.entity


class EndCreditSequence(
    name: String,
    val price: Double,
    val season: Season?,
    val episode: Episode?
): Entity(name)
