package com.vitor.myapplication.database

import com.vitor.myapplication.db.FavoriteCharactersDatabase
import com.vitor.myapplication.db.dao.FavoriteCharactersDao
import com.vitor.myapplication.db.entity.CharacterEntity
import com.vitor.myapplication.di.databaseModule
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.model.Participations
import com.vitor.myapplication.model.Thumbnail
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject
import org.junit.Assert.*
import org.koin.core.context.unloadKoinModules

@RunWith(JUnit4::class)
class FavoriteCharactersDaoTest : KoinTest {

    private val dataBase by inject<FavoriteCharactersDatabase>()
    private val dao by inject<FavoriteCharactersDao>()

    private val mockedCharacter = Character(
        1L,
        "Name 1",
        "Description 1",
        Thumbnail("path1", "ext1"),
        Participations(1),
        Participations(1),
        Participations(1),
        Participations(1)
    )

    private val mockedCharacterEntity = CharacterEntity.fromCharacter(mockedCharacter)

    @Before
    fun setUp(){
        unloadKoinModules(databaseModule)
        loadKoinModules(databaseModuleTest)
    }

    @After
    fun tearDown(){
        dataBase.close()
    }

    @Test
    fun fetchCharactersEntityListTest(){
        runBlocking {
            dao.insertCharacter(mockedCharacterEntity)
        }

        var favoriteCharacters = listOf<CharacterEntity>()

        runBlocking {
            favoriteCharacters = dao.fetchFavoriteCharacters()
        }

        assertTrue(favoriteCharacters.isNotEmpty())
    }

    @Test
    fun emptyCharacterEntityListTest(){
        var favoriteCharacters = listOf<CharacterEntity>()

        runBlocking {
            favoriteCharacters = dao.fetchFavoriteCharacters()
        }

        assertTrue(favoriteCharacters.isEmpty())
    }

    @Test
    fun insertCharacterEntityTest(){
        runBlocking {
            dao.insertCharacter(mockedCharacterEntity)
        }

        var favoriteCharacters = listOf<CharacterEntity>()

        runBlocking {
            favoriteCharacters = dao.fetchFavoriteCharacters()
        }

        val characterInserted = favoriteCharacters.map { it.toCharacter() }.contains(mockedCharacter)

        assertTrue(characterInserted)
    }

    @Test
    fun deleteCharacterEntityTest(){
        runBlocking {
            dao.insertCharacter(mockedCharacterEntity)
        }

        var favoriteCharacters = listOf<CharacterEntity>()

        runBlocking {
            favoriteCharacters = dao.fetchFavoriteCharacters()
        }

        val characterInserted = favoriteCharacters.map { it.toCharacter() }.contains(mockedCharacter)

        assertTrue(characterInserted)

        runBlocking {
            dao.deleteCharacter(mockedCharacterEntity)
            favoriteCharacters = dao.fetchFavoriteCharacters()
        }

        val characterDeleted = !favoriteCharacters.map { it.toCharacter() }.contains(mockedCharacter)

        assertTrue(characterDeleted)
    }

}