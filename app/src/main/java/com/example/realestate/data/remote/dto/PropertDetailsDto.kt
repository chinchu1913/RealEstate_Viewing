package com.example.realestate.data.remote.dto

import com.example.realestate.domain.entities.PropertyDetails

data class PropertyDetailsDto(
    val area: Int?,
    val bedrooms: Int?,
    val city: String,
    val id: Int,
    val offerType: Int?,
    val price: Int?,
    val professional: String?,
    val propertyType: String?,
    val rooms: Int?,
    val url: String?
)


fun PropertyDetailsDto.toPropertyDetails(): PropertyDetails {
    return PropertyDetails(
        area = area ?: 0,
        bedrooms = bedrooms ?: 0,
        city = city,
        id = id,
        offerType = offerType ?: 0, price = price ?: 0,
        professional = professional,
        propertyType = propertyType,
        rooms = rooms ?: 0,
        url = url
    )
}