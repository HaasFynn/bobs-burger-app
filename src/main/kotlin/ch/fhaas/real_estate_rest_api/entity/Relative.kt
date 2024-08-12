package ch.fhaas.real_estate_rest_api.entity


class Relative(
    name: String,
    val relation: String,
    wikiUrl: String
): Entity(name, wikiUrl)
