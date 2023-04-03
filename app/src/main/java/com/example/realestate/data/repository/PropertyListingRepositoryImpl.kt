package com.example.realestate.data.repository

import com.example.realestate.common.Resource
import com.example.realestate.data.remote.RealEstateApi
import com.example.realestate.data.remote.dto.toPropertyDetails
import com.example.realestate.data.remote.dto.toSpecification
import com.example.realestate.domain.entities.PropertyDetails
import com.example.realestate.domain.entities.Specification
import com.example.realestate.domain.repository.PropertyListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PropertyListingRepositoryImpl @Inject constructor(
    private val api: RealEstateApi,
) : PropertyListRepository {


    override suspend fun getPropertyList(): Flow<Resource<List<Specification>>> {
        return flow {
            emit(value = Resource.Loading(true))

            val remoteListings = try {
                val response = api.getRealEstateListing()
                response
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                null
            }
            remoteListings?.let { listings ->
                emit(Resource.Success(
                    data = listings.items.map {
                        it.toSpecification()
                    }
                ))
                emit(
                    Resource.Loading(
                        isLoading = false
                    )
                )
                return@flow
            }


        }
    }

    override suspend fun getPropertyListDetails(listingId: Int): Flow<Resource<PropertyDetails>> {
        return flow {
            emit(value = Resource.Loading(true))

            val remoteListings = try {
                val response = api.getRealEstateDetails(listingId)
                response
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                null
            }
            remoteListings?.let { listings ->
                emit(Resource.Success(
                    data = listings.toPropertyDetails()
                ))
                emit(
                    Resource.Loading(
                        isLoading = false
                    )
                )
                return@flow
            }


        }
    }


}