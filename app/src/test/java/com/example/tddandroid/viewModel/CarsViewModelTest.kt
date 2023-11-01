package com.example.tddandroid.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tddandroid.domain.UseCase
import com.example.tddandroid.listCars
import com.example.tddandroid.presenter.viewModel.CarsViewModel
import com.example.tddandroid.presenter.viewModel.StateUi
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
@OptIn(ExperimentalCoroutinesApi::class)
class CarsViewModelTest {
    private lateinit var carsViewModel: CarsViewModel
    private val useCase = mockk<UseCase>(relaxed = true)

    @get: Rule
    val instantTaskExecutor = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        carsViewModel = CarsViewModel(useCase, testDispatcher)
    }

    @Ignore
    fun `when fetch data, change stateUi and return success`() {
        carsViewModel.fetchData().also{
            assertEquals(StateUi(success = listCars), carsViewModel.stateUi.value)
        }
    }
}