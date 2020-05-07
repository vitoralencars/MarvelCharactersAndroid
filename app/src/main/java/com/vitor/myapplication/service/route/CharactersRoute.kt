package com.vitor.myapplication.service.route

import com.vitor.myapplication.model.FetchCharactersResponse
import com.vitor.myapplication.util.constant.ServiceConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersRoute {

    @GET(ServiceConstants.CHARACTERS_ENDPOINT)
    suspend fun fetchCharacters(
        @Query(ServiceConstants.CHARACTERS_NAME_QUERY) name: String?,
        @Query(ServiceConstants.OFFSET_QUERY) offset: Int,
        @Query(ServiceConstants.APIKEY_QUERY) apikey: String,
        @Query(ServiceConstants.TIMESTAMP_QUERY) timestamp: Long,
        @Query(ServiceConstants.HASH_QUERY) hash: String?,
        @Query(ServiceConstants.LIMIT_QUERY) limit: Int
    ): FetchCharactersResponse

}