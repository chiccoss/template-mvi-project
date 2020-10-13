package fr.sohayb.marvelapp.app.api.response

import kotlinx.serialization.Serializable


@Serializable
data class Data(
    val englishName: String,
    val englishNameTranslation: String,
    val name: String,
    val number: Int,
    val numberOfAyahs: Int,
    val revelationType: String
)