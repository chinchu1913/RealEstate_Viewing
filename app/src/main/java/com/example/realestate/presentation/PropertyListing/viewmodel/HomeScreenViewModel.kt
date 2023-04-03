package com.example.library.presentation.articles.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.library.presentation.articles.HomeScreenEvent
import com.example.realestate.common.Resource
import com.example.realestate.common.utils.NetworkUtil
import com.example.realestate.domain.repository.PropertyListRepository
import com.example.realestate.presentation.PropertyListing.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/*
ViewModel for home screen
 */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: PropertyListRepository,
    private val networkUtils: NetworkUtil
) :
    ViewModel() {
    var state by mutableStateOf(HomeScreenState())
    private var lastConnectionStatus = true

    init {
        viewModelScope.launch {
            getPropertyList()
            observeNetworkConnection()
        }
    }


    /*
    Method to get the article list from the repository notify the states
     */
    private fun getPropertyList() {
        viewModelScope.launch {
            repository
                .getPropertyList()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    propertyList = listings,
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
    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetPropertyList -> {
                getPropertyList()
            }
            else -> {}
        }
    }

    private suspend fun observeNetworkConnection() {
        try {
            withContext(Dispatchers.IO) {
                networkUtils.getNetworkLiveData().asFlow().collect { isConnected ->
//show the connection error is the connection status is disconnected
                    state = state.copy(showNetworkUnavailable = !isConnected)
//show the connection success message if the connection if disconnected and reconnected back.
                    val isConnectionIsBack = !lastConnectionStatus && isConnected
                    if (isConnectionIsBack) {
                        coroutineScope {
//Refresh the data once the connection is back
                            getPropertyList()
                            state = state.copy(showNetworkConnected = true)
                            delay(ViewModelConstants.CONNECTION_BACK_MSG_TIMEOUT)
                            state = state.copy(showNetworkConnected = false)
                        }
                    }
                    lastConnectionStatus = isConnected
                }
            }
        } catch (e: Exception) {
// Handle exception
        }
    }


    object ViewModelConstants {
        const val CONNECTION_BACK_MSG_TIMEOUT = 2000L
    }
}