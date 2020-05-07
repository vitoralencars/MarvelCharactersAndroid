package com.vitor.myapplication.usecase

import com.vitor.myapplication.commom.BaseTest
import com.vitor.myapplication.commom.MockedCharacters
import com.vitor.myapplication.repository.ServiceCharactersRepository
import com.vitor.myapplication.usecase.service.FetchServiceCharacters
import com.vitor.myapplication.usecase.service.ServiceCharactersUseCases
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class ServiceCharactersUseCasesTest : BaseTest() {

    private val repository = mockk<ServiceCharactersRepository>()

    private lateinit var useCases: ServiceCharactersUseCases

    override fun setUp(){
        super.setUp()
        useCases = ServiceCharactersUseCases(
            FetchServiceCharacters(repository)
        )
    }

    @Test
    fun `should call repository to fetch all characters data from service`(){
        val fetchCharactersRequest = MockedCharacters.getFetchCharacteresRequest()

        coEvery { repository.fetchCharacters(fetchCharactersRequest) } returns MockedCharacters.fetchCharactersResponse

        runBlockingTest {
            assertEquals(
                useCases.fetchServiceCharacters.invoke(fetchCharactersRequest),
                MockedCharacters.fetchCharactersResponse
            )
        }

        coVerify(timeout = 5000) { repository.fetchCharacters(fetchCharactersRequest) }
    }

    @Test
    fun `should call repository to fetch characters data by name from service`(){
        val fetchCharactersRequest = MockedCharacters.getFetchCharacteresRequestByName()

        coEvery { repository.fetchCharacters(fetchCharactersRequest) } returns MockedCharacters.fetchCharactersResponse

        runBlockingTest {
            assertEquals(
                useCases.fetchServiceCharacters.invoke(fetchCharactersRequest),
                MockedCharacters.fetchCharactersResponse
            )
        }

        coVerify(timeout = 5000) { repository.fetchCharacters(fetchCharactersRequest) }
    }


}