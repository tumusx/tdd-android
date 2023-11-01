package com.example.tddandroid.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tddandroid.data.StateRequest
import com.example.tddandroid.data.model.Cars
import com.example.tddandroid.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class StateUi(
    var errorMessage: String? = null,
    var loading: Boolean = true,
    var success: List<Cars>? = null
)

class CarsViewModel(private val useCase: UseCase,
private val coroutineDispatcher: CoroutineDispatcher) : ViewModel() {
    private val _stateUi: MutableStateFlow<StateUi> = MutableStateFlow(StateUi(loading = true))
    val stateUi: StateFlow<StateUi> = _stateUi

    fun fetchData() {
        viewModelScope.launch(coroutineDispatcher) {
            useCase.getAllCars().collect {
                if (it.messageError.isNullOrEmpty()) {
                    _stateUi.value = StateUi(errorMessage = it.messageError)
                } else {
                    _stateUi.value =
                        StateUi(success = (it as StateRequest.SuccessGetItems).copy().listItems)
                }
            }
        }
    }
}