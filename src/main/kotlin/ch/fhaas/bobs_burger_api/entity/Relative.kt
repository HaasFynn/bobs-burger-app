package ch.fhaas.bobs_burger_api.entity


class Relative(
    name: String,
    val relation: String,
    wikiUrl: String
): Entity(name, wikiUrl)
