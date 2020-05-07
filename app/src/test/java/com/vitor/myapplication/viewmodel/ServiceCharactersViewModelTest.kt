package com.vitor.myapplication.viewmodel

import androidx.lifecycle.Observer
import com.vitor.myapplication.commom.BaseTest
import com.vitor.myapplication.commom.MockedCharacters
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.usecase.service.ServiceCharactersUseCases
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class ServiceCharactersViewModelTest : BaseTest(){

    private val useCases = mockk<ServiceCharactersUseCases>()

    private lateinit var viewModel: ServiceCharactersViewModel

    private val charactersObserver = mockk<Observer<List<Character>>>(relaxed = true)

    override fun setUp(){
        super.setUp()
        viewModel = ServiceCharactersViewModel(useCases)
    }

    @Test
    fun `should call usecases to fetch all characters data from service`(){
        viewModel.charactersLiveData().observeForever(charactersObserver)

        coEvery { useCases.fetchServiceCharacters(any()) } returns MockedCharacters.fetchCharactersResponse

        viewModel.fetchAllCharacters()

        coVerify(timeout = 5000) { useCases.fetchServiceCharacters(any()) }
        verify { charactersObserver.onChanged(MockedCharacters.fetchCharactersResponse.data.characters) }
    }

    @Test
    fun `should call usecases to fetch characters data by name from service`(){
        viewModel.charactersLiveData().observeForever(charactersObserver)

        coEvery { useCases.fetchServiceCharacters(any()) } returns MockedCharacters.fetchCharactersResponse

        viewModel.fetchCharactersByName("")

        coVerify(timeout = 5000) { useCases.fetchServiceCharacters(any()) }
        verify { charactersObserver.onChanged(MockedCharacters.fetchCharactersResponse.data.characters) }
    }

}