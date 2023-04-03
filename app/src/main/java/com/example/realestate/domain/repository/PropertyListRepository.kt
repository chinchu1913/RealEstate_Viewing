package com.example.realestate.domain.repository

import com.example.realestate.common.Resource
import com.example.realestate.domain.entities.PropertyDetails
import com.example.realestate.domain.entities.Specification
import kotlinx.coroutines.flow.Flow

interface PropertyListRepository {
    suspend fun getPropertyList(): Flow<Resource<List<Specification>>>

    suspend fun getPropertyListDetails(listingId:Int): Flow<Resource<PropertyDetails>>
}