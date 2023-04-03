package com.example.realestate.presentation.PropertyListing

import com.example.realestate.domain.entities.Specification

/*
States in home screen
 */
data class HomeScreenState(
    val propertyList: List<Specification> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
    val showNetworkUnavailable: Boolean = false,
    val showNetworkConnected: Boolean = false,
)
