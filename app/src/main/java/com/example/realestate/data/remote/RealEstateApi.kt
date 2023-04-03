package com.example.realestate.data.remote

import com.example.realestate.data.remote.dto.PropertyDetailsDto
import com.example.realestate.data.remote.dto.PropertyListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RealEstateApi {
    @GET("/listings.json")
    suspend fun getRealEstateListing(): PropertyListDto

    @GET("/listings/{listingId}.json")
    suspend fun getRealEstateDetails(@Path("listingId") listingId: Int): PropertyDetailsDto

}