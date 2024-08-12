package ch.fhaas.real_estate_rest_api.entity

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class Entity protected constructor(
    val name: String = "",
    val wikiUrl: String = ""
)
