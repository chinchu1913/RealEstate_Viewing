package com.example.realestate.data.repository

import com.example.realestate.common.Resource
import com.example.realestate.data.remote.RealEstateApi
import com.example.realestate.domain.entities.Specification
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import com.google.common.truth.Truth.assertThat
import org.mockito.kotlin.mock
class PropertyListingRepositoryImplTest

{
    private lateinit var mockApi: RealEstateApi
    private lateinit var repository: PropertyListingRepositoryImpl
    private lateinit var samplePropertyEntity: Specification


    @Before
    fun setUp() {
        mockApi = mock()
        repository = mock()
        repository = PropertyListingRepositoryImpl(mockApi)
        samplePropertyEntity = Specification(
            bedrooms= 7,
        city= "Deauville",
        id= 2,
        area= 600,
        url="https://v.seloger.com/s/crop/590x330/visuels/2/a/l/s/2als8bgr8sd2vezcpsj988mse4olspi5rfzpadqok.jpg",
        price=3500000,
        professional= "GSL STICKINESS",
        propertyType="Maison - Villa",
        offerType= 2,
        rooms= 11
        )
    }
    @Test
    fun `is loading is true on getSearchList`() = runBlocking {
        Mockito.`when`(mockApi.getRealEstateListing()).thenReturn(null)
        val firstItem = repository.getPropertyList().first()
        assertThat((firstItem as Resource.Loading).isLoading).isEqualTo(true)
    }
}