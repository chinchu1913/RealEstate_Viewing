package com.example.realestate.presentation.propertyDetails

import com.example.realestate.domain.entities.PropertyDetails


data class DetailsScreenState(
    val details: PropertyDetails? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
)