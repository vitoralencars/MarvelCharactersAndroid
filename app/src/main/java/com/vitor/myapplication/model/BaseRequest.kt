package com.vitor.myapplication.model

import com.google.gson.annotations.SerializedName
import com.vitor.myapplication.util.constant.ServiceConstants
import com.vitor.myapplication.util.RequestsHelper

open class BaseRequest {
    @SerializedName("apikey")
    var apikey: String = ServiceConstants.PUBLIC_KEY
    @SerializedName("ts")
    var timestamp: Long = System.currentTimeMillis()
    @SerializedName("hash")
    var hash: String? = RequestsHelper.getMd5Hash(
        timestamp,
        ServiceConstants.PRIVATE_KEY,
        ServiceConstants.PUBLIC_KEY
    )
}