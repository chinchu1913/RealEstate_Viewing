package com.example.realestate.presentation.propertyDetails


sealed class DetailsScreenEvent {
    class GetPropertyDetails(val propertyId: Int) : DetailsScreenEvent()
}