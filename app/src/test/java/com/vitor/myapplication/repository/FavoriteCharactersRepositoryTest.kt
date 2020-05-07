package com.vitor.myapplication.repository

import com.vitor.myapplication.commom.BaseTest
import com.vitor.myapplication.commom.MockedCharacters
import com.vitor.myapplication.db.datasource.FavoriteCharactersDataSource
import com.vitor.myapplication.model.Character
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoriteCharactersRepositoryTest : BaseTest() {

    private val dataSource = mockk<FavoriteCharactersDataSource>()

    private lateinit var repository: FavoriteCharactersRepository

    override fun setUp() {
        super.setUp()
        repository = FavoriteCharactersRepository(dataSource)
    }

    @Test
    fun `should call data source to fetch favorite characters data from database`(){
        coEvery { dataSource.fetchFavoriteCharacters() } returns MockedCharacters.charactersList


        runBlockingTest {
            assertEquals(
                repository.fetchFavoriteCharacters(),
                MockedCharacters.charactersList
            )
        }

        coVerify(timeout = 5000) { dataSource.fetchFavoriteCharacters() }
    }

    @Test
    fun `should call data source to insert favorite character data into database`(){
        val character = mockk<Character>()
        coEvery { dataSource.insertFavoriteCharacter(character) } just Runs

        runBlockingTest {
            repository.insertFavoriteCharacter(character)
        }

        coVerify(timeout = 5000) { repository.insertFavoriteCharacter(character) }
    }

    @Test
    fun `should call data source to delete favorite character data from database`(){
        val character = mockk<Character>()
        coEvery { dataSource.deleteFavoriteCharacter(character) } just Runs

        runBlockingTest {
            repository.deleteFavoriteCharacter(character)
        }

        coVerify(timeout = 5000) { repository.deleteFavoriteCharacter(character) }
    }

}