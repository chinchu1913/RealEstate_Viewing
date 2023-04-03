package com.example.realestate.domain.entities


data class  PropertyDetails(
    val area: Int,
    val bedrooms: Int,
    val city: String?,
    val id: Int,
    val offerType: Int,
    val price: Int,
    val professional: String?,
    val propertyType: String?,
    val rooms: Int,
    val url: String?
)