package com.vitor.myapplication.viewmodel

import androidx.lifecycle.Observer
import com.vitor.myapplication.commom.BaseTest
import com.vitor.myapplication.commom.MockedCharacters
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.usecase.database.FavoriteCharactersUseCases
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoriteCharactersViewModelTest : BaseTest(){

    private val useCases = mockk<FavoriteCharactersUseCases>()

    private lateinit var viewModel: FavoriteCharactersViewModel

    override fun setUp(){
        super.setUp()
        viewModel = FavoriteCharactersViewModel(useCases)
    }

    @Test
    fun `should call usecase to fetch favorite characters data from database`(){
        val favoritesObserver = mockk<Observer<List<Character>>>(relaxed = true)
        val staticFavoritesObserver = mockk<Observer<List<Character>>>(relaxed = true)

        viewModel.favoritesLiveData().observeForever(favoritesObserver)
        FavoriteCharactersViewModel.staticFavorites.observeForever(staticFavoritesObserver)

        coEvery { useCases.fetchFavoriteCharacters() } returns MockedCharacters.charactersList

        viewModel.fetchFavoriteCharacters()

        coVerify(timeout = 5000) { useCases.fetchFavoriteCharacters() }
        verify { favoritesObserver.onChanged(MockedCharacters.charactersList) }
        verify { staticFavoritesObserver.onChanged(MockedCharacters.charactersList) }
    }

    @Test
    fun `should call usecases when insert character to database`(){
        val character = mockk<Character>()

        coEvery { useCases.insertFavoriteCharacter(character) } just Runs

        viewModel.insertFavoriteCharacter(character)

        coVerify(timeout = 5000) { useCases.insertFavoriteCharacter(character) }
    }

    @Test
    fun `should call usecases when delete character from database`(){
        val character = mockk<Character>()

        coEvery { useCases.deleteFavoriteCharacter(character) } just Runs

        viewModel.deleteFavoriteCharacter(character)

        coVerify(timeout = 5000) { useCases.deleteFavoriteCharacter(character) }
    }

}