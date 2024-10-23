package com.adyen.android.assignment.data.repository

import com.adyen.android.assignment.api.VenueRecommendationsQueryBuilder
import com.adyen.android.assignment.common.Resource
import com.adyen.android.assignment.data.converter.NearByPlaceConverter
import com.adyen.android.assignment.data.datasource.PlacesRemoteDataSource
import com.adyen.android.assignment.domain.model.Place
import com.adyen.android.assignment.domain.repository.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlacesRemoteDataSource,
    private val venueRecommendationsQueryBuilder: VenueRecommendationsQueryBuilder,
    private val nearByPlaceConverter: NearByPlaceConverter,
) : PlacesRepository {

    override fun getNearbyPlaces(lat: Double, lng: Double): Flow<Resource<List<Place>>> {
        val params = venueRecommendationsQueryBuilder.setLatitudeLongitude(lat, lng).build()
         return remoteDataSource.getNearbyPlaces(params).map { res ->
            when(res) {
                is Resource.Success -> Resource.Success(nearByPlaceConverter.convert(res.data))
                is Resource.Failed -> Resource.Failed(res.error)
            }
        }
    }
}
