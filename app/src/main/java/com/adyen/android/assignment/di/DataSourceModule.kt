package com.adyen.android.assignment.di

import com.adyen.android.assignment.data.datasource.PlacesRemoteDataSource
import com.adyen.android.assignment.data.datasource.PlacesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindPlaceRemoteDataSource(placesRemoteDataSourceImpl: PlacesRemoteDataSourceImpl): PlacesRemoteDataSource
}
