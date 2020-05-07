package com.vitor.myapplication.model

import com.google.gson.annotations.SerializedName
import com.vitor.myapplication.util.constant.ServiceConstants

data class FetchCharactersRequest(
    @SerializedName("name")
    val name: String?,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int = ServiceConstants.VALUES_LIMIT
): BaseRequest()

data class FetchCharactersResponse(
    @SerializedName("data")
    val data: Data
)