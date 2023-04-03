package com.example.realestate.presentation.propertyDetails.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestate.common.Resource
import com.example.realestate.domain.repository.PropertyListRepository
import com.example.realestate.presentation.propertyDetails.DetailsScreenEvent
import com.example.realestate.presentation.propertyDetails.DetailsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: PropertyListRepository,
) :
    ViewModel() {


    var state by mutableStateOf(DetailsScreenState())


    /*
    Method to get the article list from the repository notify the states
     */
    private fun getDetails(id: Int) {
        viewModelScope.launch {
            repository
                .getPropertyListDetails(id)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    details = listings,
                                    isError = false
                                )
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                isError = true
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    /*
    Method to handle the  events from the UI
     */
    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.GetPropertyDetails -> {
                getDetails(event.propertyId)
            }
        }
    }

}



