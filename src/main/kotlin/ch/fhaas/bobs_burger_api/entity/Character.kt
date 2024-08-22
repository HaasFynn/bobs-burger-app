package ch.fhaas.bobs_burger_api.entity

class Character(
    name: String,
    wikiUrl: String,
    val relatives: List<Relative?> = emptyList(),
    val imageUrl: String?,
    val gender: String,
    val age: Int?,
    val hair: String?,
    val occupation: Occupation?,
    val allOccupations: List<Occupation> = emptyList(),
    val firstEpisode: Episode?,
    val voicedBy: VoiceActor?,
) : Entity(name, wikiUrl) {
    companion object {
        @JvmStatic
        val searchKeys: Array<String> = arrayOf(
            "limit", "name", "gender", "age", "hair", "occupation", "episode", "voiceActor",
        )
    }
}