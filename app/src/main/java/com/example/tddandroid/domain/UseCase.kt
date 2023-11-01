package com.example.tddandroid.domain

import com.example.tddandroid.data.Repository
import com.example.tddandroid.data.StateRequest
import com.example.tddandroid.data.model.Cars
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class UseCase(private val repository: Repository) {
    companion object {
        const val MIN_CHARS_MODEL_CAR = 5
    }

    suspend fun getAllCars(): Flow<StateRequest> = flow {
        repository.getAllItems().collect {
            emit(it)
        }
    }

     fun insertCars(cars: Cars): Flow<StateRequest> = flow {
        if (cars.modelCar.count() < MIN_CHARS_MODEL_CAR) {
            emit(StateRequest.Error("Tamanho do modelo do carro incompatível com a regra de 5 caracteres. Tente com um nome maior."))
        } else {
            repository.insertItems(cars).collect { state ->
                val result = if (state is StateRequest.Error) {
                    StateRequest.Error("Ops... Aconteceu um erro inesperado.")
                } else {
                    StateRequest.SuccessInsert
                }
                emit(result)
            }
        }
    }
}
