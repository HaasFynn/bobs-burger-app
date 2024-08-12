package ch.fhaas.real_estate_rest_api.entity

class Character(
    name: String,
    wikiUrl: String,
    val relatives: List<Relative>,
    val imageUrl: String,
    val gender: String?,
    val age: Int?,
    val hair: String?,
    val occupation: Occupation?,
    val allOccupations: List<Occupation>,
    val firstEpisode: Episode?,
    val voicedBy: VoiceActor?,
) : Entity(name, wikiUrl)