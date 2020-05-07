package com.vitor.myapplication.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("total")
    val total: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val characters: List<Character>
)