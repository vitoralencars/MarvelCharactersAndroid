package com.vitor.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.model.FetchCharactersRequest
import com.vitor.myapplication.model.FetchCharactersResponse
import com.vitor.myapplication.usecase.service.ServiceCharactersUseCases
import com.vitor.myapplication.util.ProgressBarHandler
import com.vitor.myapplication.util.constant.ServiceConstants
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServiceCharactersViewModel(private val useCases: ServiceCharactersUseCases): BaseViewModel() {

    private var currentOffset = 0
    private var charactersCount = 0
    private var hasMoreValues = true

    private val characters = MutableLiveData<List<Character>>()
    fun charactersLiveData(): LiveData<List<Character>> = characters

    fun fetchAllCharacters(){
        if(hasMoreValues) {
            launch {
                ProgressBarHandler.showProgressBar()

                withContext(coroutineContext) {
                    checkResponse(useCases.fetchServiceCharacters(
                        FetchCharactersRequest(
                            null,
                            currentOffset
                        )
                    ))
                }
            }
        }
    }

    fun fetchCharactersByName(name: String){
        if(hasMoreValues) {
            launch {
                ProgressBarHandler.showProgressBar()

                withContext(coroutineContext) {
                    checkResponse(useCases.fetchServiceCharacters(
                        FetchCharactersRequest(
                            name,
                            currentOffset
                        )
                    ))
                }
            }
        }
    }

    private fun checkResponse(response: FetchCharactersResponse?){
        response?.let {
            charactersCount += it.data.count
            hasMoreValues = charactersCount < it.data.total
            characters.value = it.data.characters
        }
    }

    fun updateOffset(){
        currentOffset += ServiceConstants.VALUES_LIMIT
    }

    fun resetState(){
        currentOffset = 0
        charactersCount = 0
        hasMoreValues = true
    }

}