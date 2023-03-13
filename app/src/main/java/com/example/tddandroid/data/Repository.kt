package com.example.tddandroid.data

import com.example.tddandroid.data.model.Cars
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class Repository(private val databaseApplication: DatabaseApplication) {

    fun getAllItems(): Flow<StateRequest> = flow {
        val result = databaseApplication.getAllItems()
        if (result.isEmpty()) {
            emit(StateRequest.Error("Sem dados cadastrados sobre os carros! Tente inserir."))
        } else {
            emit(StateRequest.SuccessGetItems(result))
        }
    }


    fun insertItems(items: Cars): Flow<StateRequest> = flow {
        databaseApplication.insertItems(items)
        try {
            emit(StateRequest.SuccessInsert)
        } catch (exception: Exception) {
            emit(StateRequest.Error(errorMessage = "erro ao salvar dados."))
        }
    }

}