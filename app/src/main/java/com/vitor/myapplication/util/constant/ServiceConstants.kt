package com.vitor.myapplication.util.constant

object ServiceConstants {

    const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    const val PUBLIC_KEY = "9a3a45492c383a087dba9dc7384c5c42"
    const val PRIVATE_KEY = "87d0aeb83743cd5213ecebeb91c958b0e6b2074b"
    const val APIKEY_QUERY = "apikey"
    const val TIMESTAMP_QUERY = "ts"
    const val HASH_QUERY = "hash"
    const val VALUES_LIMIT = 20
    const val TIMEOUT_SECONDS = 60L

    //CHARACTER REQUEST
    const val CHARACTERS_ENDPOINT = "characters"
    const val CHARACTERS_NAME_QUERY = "name"
    const val OFFSET_QUERY = "offset"
    const val LIMIT_QUERY = "limit"

}