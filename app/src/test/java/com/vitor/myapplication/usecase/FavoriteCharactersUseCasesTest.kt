package com.vitor.myapplication.usecase

import com.vitor.myapplication.commom.BaseTest
import com.vitor.myapplication.commom.MockedCharacters
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.repository.FavoriteCharactersRepository
import com.vitor.myapplication.usecase.database.DeleteFavoriteCharacter
import com.vitor.myapplication.usecase.database.FavoriteCharactersUseCases
import com.vitor.myapplication.usecase.database.FetchFavoriteCharacters
import com.vitor.myapplication.usecase.database.InsertFavoriteCharacter
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class FavoriteCharactersUseCasesTest : BaseTest(){
    private val repository = mockk<FavoriteCharactersRepository>()

    private lateinit var favoriteCharactersUseCases: FavoriteCharactersUseCases

    override fun setUp(){
        super.setUp()
        favoriteCharactersUseCases = FavoriteCharactersUseCases(
            FetchFavoriteCharacters(repository),
            InsertFavoriteCharacter(repository),
            DeleteFavoriteCharacter(repository)
        )
    }

    @Test
    fun `should call repository to fetch favorite characters data from database`(){
        coEvery { repository.fetchFavoriteCharacters() } returns MockedCharacters.charactersList

        runBlockingTest {
            assertEquals(
                favoriteCharactersUseCases.fetchFavoriteCharacters.invoke(),
                MockedCharacters.charactersList
            )
        }

        coVerify(timeout = 5000) { repository.fetchFavoriteCharacters() }
    }

    @Test
    fun `should call repository to insert favorite character data into database`(){
        val character = mockk<Character>()
        coEvery { repository.insertFavoriteCharacter(character) } just Runs

        runBlockingTest {
            favoriteCharactersUseCases.insertFavoriteCharacter.invoke(character)
        }

        coVerify(timeout = 5000) { repository.insertFavoriteCharacter(character) }
    }

    @Test
    fun `should call repository to delete favorite character data from database`(){
        val character = mockk<Character>()
        coEvery { repository.deleteFavoriteCharacter(character) } just Runs

        runBlockingTest {
            favoriteCharactersUseCases.deleteFavoriteCharacter.invoke(character)
        }

        coVerify(timeout = 5000) { repository.deleteFavoriteCharacter(character) }
    }

}