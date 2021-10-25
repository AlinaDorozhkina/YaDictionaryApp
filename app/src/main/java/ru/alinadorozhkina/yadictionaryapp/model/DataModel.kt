package ru.alinadorozhkina.yadictionaryapp.model

import com.google.gson.annotations.SerializedName

data class Def(
    @SerializedName("text")
    val text: String,
    @SerializedName("ts")
    val ts: String,
    @SerializedName("tr")
    val tr: List<Meaning>
)

data class Meaning(
    @SerializedName("text")
    val text: String,
    @SerializedName("pos")
    val pos: String,
    @SerializedName("gen")
    val gen: String,
    @SerializedName("ex")
    val example: List<Example>?
)

data class Example(
    @SerializedName("text")
    val text: String?,
    @SerializedName("tr")
    val translation: List<Translation>?
)

data class Translation(
    @SerializedName("text")
    val translation: String?
)

data class DataModel(
    @SerializedName("def")
    val def: List<Def>
)