package com.adyen.android.assignment.domain.usecase

import com.adyen.android.assignment.common.Resource
import com.adyen.android.assignment.common.exceptions.ServerUnreachableException
import com.adyen.android.assignment.domain.model.Place
import com.adyen.android.assignment.domain.repository.PlacesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NearByPlacesUseCaseTest {

    @Mock
    lateinit var placesRepository: PlacesRepository

    @InjectMocks
    lateinit var subject: NearByPlacesUseCase

    @Test
    fun `invoke should return success when repository succeeds`() = runTest {
        val lat = 52.0
        val lng = 4.0
        val placeList = listOf(mock(Place::class.java))
        val result = Resource.Success(placeList)

        `when`(placesRepository.getNearbyPlaces(lat, lng)).thenReturn(flowOf(result))

        val actualResult = subject(lat, lng).first()

        assertEquals(placeList, (actualResult as Resource.Success).data)
    }

    @Test
    fun `invoke should return failure when repository fails`() = runTest {
        val lat = 52.0
        val lng = 4.0
        val exception = ServerUnreachableException()
        val result = Resource.Failed<List<Place>>(exception)

        `when`(placesRepository.getNearbyPlaces(lat, lng)).thenReturn(flowOf(result))

        val actualResult = subject(lat, lng).first()

        assertEquals(exception, (actualResult as Resource.Failed).error)
    }
}
