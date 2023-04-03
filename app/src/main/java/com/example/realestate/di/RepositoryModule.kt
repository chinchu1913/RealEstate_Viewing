package com.example.realestate.di

import com.example.realestate.data.repository.PropertyListingRepositoryImpl
import com.example.realestate.domain.repository.PropertyListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun buildPropertyListRepository(
        propertyListingRepositoryImpl: PropertyListingRepositoryImpl,
    ): PropertyListRepository
}
