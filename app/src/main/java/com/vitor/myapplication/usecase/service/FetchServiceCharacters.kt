package com.vitor.myapplication.usecase.service

import com.vitor.myapplication.model.FetchCharactersRequest
import com.vitor.myapplication.repository.ServiceCharactersRepository

class FetchServiceCharacters(private val repository: ServiceCharactersRepository) {
    suspend operator fun invoke(request: FetchCharactersRequest) = repository.fetchCharacters(request)
}