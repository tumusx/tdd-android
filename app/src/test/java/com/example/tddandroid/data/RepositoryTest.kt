package com.example.tddandroid.data

import com.example.tddandroid.data.model.Cars
import com.example.tddandroid.listCars
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryTest {
    private val database = mockk<DatabaseApplication>(relaxed = true)
    lateinit var repository: Repository


    @Before
    fun setup() {
        repository = Repository(database)
    }

    @Test
    fun `when call getListItems, return Error - emptyList`() {
        val items = repository.getAllItems()
        runBlocking {
            items.collect { state ->
                assertEquals(
                    state, StateRequest.Error(
                        "Sem dados cadastrados sobre os carros! Tente inserir."
                    )
                )
            }
        }
    }

    @Test
    fun `when call insertItems, return success`() {
        repository.insertItems(listCars.first()).also { result ->
            runBlocking {
                result.collect { resultState ->
                    assertEquals(resultState, StateRequest.SuccessInsert)
                }
            }
        }
    }
}