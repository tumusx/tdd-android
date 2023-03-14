package com.example.tddandroid.domain

import com.example.tddandroid.data.Repository
import com.example.tddandroid.data.StateRequest
import com.example.tddandroid.data.model.Cars
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UseCaseTest {
    private val repository = mockk<Repository>(relaxed = true)
    private lateinit var useCase: UseCase


    private fun car(modelCar: String): Cars = Cars(modelCar, 2022, 150, false, 120000)

    @Before
    fun setup() {
        useCase = UseCase(repository)
    }

    @Test
    fun `when insert car model less than 5 chars, returns error message`() = runBlocking {
        useCase.insertCars(car("hb20")).collect {
        assertEquals(
            StateRequest.Error("Tamanho do modelo do carro incompatível com a regra de 5 caracteres. Tente com um nome maior."),
            it
        )
    }
}


@Test
fun `when insert car name more 5 chars, return success`(): Unit = runBlocking {
    useCase.insertCars(car("peugeot")).also {
        verify { repository.insertItems(any()) }
    }.collect { resultRequest ->
        assertEquals(StateRequest.SuccessInsert, resultRequest)
    }
}
}