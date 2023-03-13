package com.example.tddandroid.data

import com.example.tddandroid.data.model.Cars

sealed class StateRequest(val messageError: String? = null) {
    object SuccessInsert : StateRequest()
    data class SuccessGetItems(val listItems: List<Cars>) : StateRequest()
    data class Error(val errorMessage: String?) : StateRequest(errorMessage)
}