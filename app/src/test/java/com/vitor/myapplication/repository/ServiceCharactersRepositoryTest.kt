package com.vitor.myapplication.repository

import com.vitor.myapplication.commom.BaseTest
import com.vitor.myapplication.commom.MockedCharacters
import com.vitor.myapplication.model.FetchCharactersResponse
import com.vitor.myapplication.service.route.CharactersRoute
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class ServiceCharactersRepositoryTest : BaseTest(){

    private val route = mockk<CharactersRoute>()

    private lateinit var repository: ServiceCharactersRepository

    override fun setUp() {
        super.setUp()
        repository = ServiceCharactersRepository(route)
    }

    @Test
    fun `should return null when repository throws an exception`(){
        val fetchCharactersRequest = MockedCharacters.getFetchCharacteresRequest()

        coEvery { route.fetchCharacters(
            fetchCharactersRequest.name,
            fetchCharactersRequest.offset,
            fetchCharactersRequest.apikey,
            fetchCharactersRequest.timestamp,
            fetchCharactersRequest.hash,
            fetchCharactersRequest.limit
        ) } throws Exception()

        runBlockingTest {
            assertNull(repository.fetchCharacters(fetchCharactersRequest))
        }
    }

    @Test
    fun `should call route to fetch all characters data from service`(){
        val fetchCharactersRequest = MockedCharacters.getFetchCharacteresRequest()

        coEvery { route.fetchCharacters(
            fetchCharactersRequest.name,
            fetchCharactersRequest.offset,
            fetchCharactersRequest.apikey,
            fetchCharactersRequest.timestamp,
            fetchCharactersRequest.hash,
            fetchCharactersRequest.limit
        ) } returns MockedCharacters.fetchCharactersResponse

        runBlockingTest {
            assertEquals(
                repository.fetchCharacters(fetchCharactersRequest),
                MockedCharacters.fetchCharactersResponse
            )
        }

        coVerify(timeout = 5000) { route.fetchCharacters(
            fetchCharactersRequest.name,
            fetchCharactersRequest.offset,
            fetchCharactersRequest.apikey,
            fetchCharactersRequest.timestamp,
            fetchCharactersRequest.hash,
            fetchCharactersRequest.limit
        ) }
    }

    @Test
    fun `should call route to fetch characters data by name from service`(){
        val fetchCharactersRequest = MockedCharacters.getFetchCharacteresRequestByName()

        coEvery { route.fetchCharacters(
            fetchCharactersRequest.name,
            fetchCharactersRequest.offset,
            fetchCharactersRequest.apikey,
            fetchCharactersRequest.timestamp,
            fetchCharactersRequest.hash,
            fetchCharactersRequest.limit
        ) } returns MockedCharacters.fetchCharactersResponse

        runBlockingTest {
            assertEquals(
                repository.fetchCharacters(fetchCharactersRequest),
                MockedCharacters.fetchCharactersResponse
            )
        }

        coVerify(timeout = 5000) { route.fetchCharacters(
            fetchCharactersRequest.name,
            fetchCharactersRequest.offset,
            fetchCharactersRequest.apikey,
            fetchCharactersRequest.timestamp,
            fetchCharactersRequest.hash,
            fetchCharactersRequest.limit
        ) }
    }
}