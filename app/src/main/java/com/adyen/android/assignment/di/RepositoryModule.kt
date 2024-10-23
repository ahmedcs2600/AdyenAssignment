package com.adyen.android.assignment.di

import com.adyen.android.assignment.data.repository.PlacesRepositoryImpl
import com.adyen.android.assignment.domain.repository.PlacesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindPlacesRepository(placesRepositoryImpl: PlacesRepositoryImpl): PlacesRepository
}
