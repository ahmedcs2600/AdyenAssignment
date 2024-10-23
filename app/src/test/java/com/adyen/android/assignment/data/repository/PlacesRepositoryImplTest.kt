package com.adyen.android.assignment.data.repository

import com.adyen.android.assignment.api.VenueRecommendationsQueryBuilder
import com.adyen.android.assignment.api.model.Result
import com.adyen.android.assignment.common.Resource
import com.adyen.android.assignment.common.exceptions.NetworkException
import com.adyen.android.assignment.common.exceptions.ServerUnreachableException
import com.adyen.android.assignment.data.converter.NearByPlaceConverter
import com.adyen.android.assignment.data.datasource.PlacesRemoteDataSource
import com.adyen.android.assignment.domain.model.Place
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.Result as KResult

@RunWith(MockitoJUnitRunner::class)
class PlacesRepositoryImplTest {

    @Mock
    private lateinit var remoteDataSource: PlacesRemoteDataSource

    @Mock
    private lateinit var venueRecommendationsQueryBuilder: VenueRecommendationsQueryBuilder

    @Mock
    private lateinit var nearByPlaceConverter: NearByPlaceConverter

    private lateinit var placesRepository: PlacesRepositoryImpl

    @Before
    fun setUp() {
        placesRepository = PlacesRepositoryImpl(
            remoteDataSource,
            venueRecommendationsQueryBuilder,
            nearByPlaceConverter
        )
    }

    @Test
    fun `getNearbyPlaces should return success when remote data source succeeds`() = runTest {
        val lat = 52.379189
        val lng = 4.899431
        val queryParams = mapOf("ll" to "$lat,$lng")
        val convertedPlaces = PlacesRepositoryMocks.mockPlaces()
        val nearByPlaces = listOf(mock(Result::class.java))
        `when`(venueRecommendationsQueryBuilder.setLatitudeLongitude(lat, lng)).thenReturn(
            venueRecommendationsQueryBuilder
        )
        `when`(venueRecommendationsQueryBuilder.build()).thenReturn(queryParams)
        `when`(remoteDataSource.getNearbyPlaces(queryParams)).thenReturn(flowOf(Resource.Success(nearByPlaces)))
        `when`(nearByPlaceConverter.convert(nearByPlaces)).thenReturn(convertedPlaces)

        val resultFlow: Flow<Resource<List<Place>>> = placesRepository.getNearbyPlaces(lat, lng)

        val resultList = resultFlow.first()
        assertEquals(Resource.Success(convertedPlaces), resultList)
    }

    @Test
    fun `getNearbyPlaces should return failure when remote data source fails`() = runTest {
        val lat = 52.379189
        val lng = 4.899431
        val error = ServerUnreachableException()
        val queryParams = mapOf("ll" to "$lat,$lng")
        `when`(venueRecommendationsQueryBuilder.setLatitudeLongitude(lat, lng)).thenReturn(
            venueRecommendationsQueryBuilder
        )
        `when`(venueRecommendationsQueryBuilder.build()).thenReturn(queryParams)
        `when`(remoteDataSource.getNearbyPlaces(queryParams)).thenReturn(flowOf(Resource.Failed(error)))

        val resultFlow: Flow<Resource<List<Place>>> = placesRepository.getNearbyPlaces(lat, lng)

        val resultList = resultFlow.first()
        assertEquals(Resource.Failed<List<Place>>(error), resultList)
    }
}
