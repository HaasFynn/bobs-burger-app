package ch.fhaas.real_estate_rest_api.entity

class Season(
    val seasonNum: Int,
    val episodes: List<Episode?>
): Entity()
