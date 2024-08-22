package ch.fhaas.bobs_burger_api.entity

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
abstract class Entity protected constructor(
    val name: String = "",
    val wikiUrl: String = ""
)
