package com.vitor.myapplication.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("comics")
    val comics: Participations,
    @SerializedName("stories")
    val stories: Participations,
    @SerializedName("series")
    val series: Participations,
    @SerializedName("events")
    val events: Participations
)

data class Thumbnail(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)

data class Participations(
    @SerializedName("available")
    val available: Int
)