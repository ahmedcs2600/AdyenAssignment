package com.adyen.android.assignment.ui.viewmodel

import com.adyen.android.assignment.common.Resource
import com.adyen.android.assignment.common.exceptions.ServerUnreachableException
import com.adyen.android.assignment.domain.model.Place
import com.adyen.android.assignment.domain.usecase.NearByPlacesUseCase
import com.adyen.android.assignment.ui.model.PlacesScreenUiState
import com.adyen.android.assignment.utils.MainDispatcherRule
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlacesViewModelTest {

    companion object {
        private const val LAT = 0.0
        private const val LNG = 0.0
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var nearByPlacesUseCase: NearByPlacesUseCase

    @Mock
    private lateinit var placesConverter: PlacesConverter

    @InjectMocks
    private lateinit var viewModel: PlacesViewModel

    @Test
    fun `When fetchNearByPlaces emits success then it updates the UI state`()  {
        val result = listOf(mock(Place::class.java))
        `when`(nearByPlacesUseCase.invoke(LAT, LNG)).thenReturn(flowOf(Resource.Success(result)))
        val expected = PlacesScreenUiState(
            isLoading = false,
            data = result,
            error = null
        )
        viewModel.fetchNearByPlaces(LAT, LNG)

        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `When fetchNearByPlaces emits error then it updates the UI state`()  {
        val error = ServerUnreachableException()
        val errorMessage = "Some error"
        `when`(nearByPlacesUseCase.invoke(LAT, LNG)).thenReturn(flowOf(Resource.Failed(error)))
        `when`(placesConverter.convertToError(error)).thenReturn(errorMessage)
        val expected = PlacesScreenUiState(
            isLoading = false,
            error = errorMessage
        )
        viewModel.fetchNearByPlaces(LAT, LNG)

        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `onGetCurrentLocationFailed should set error`() {
        val errorMessage = "Location Error"
        `when`(placesConverter.getLocationFailedMessage()).thenReturn(errorMessage)
        val expected = PlacesScreenUiState(
            isLoading = false,
            error = errorMessage
        )

        viewModel.onGetCurrentLocationFailed()

        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `onPermissionDenied should set error`() {
        val errorMessage = "Permission Denied Error"
        `when`(placesConverter.getPermissionDeniedMessage()).thenReturn(errorMessage)
        val expected = PlacesScreenUiState(
            isLoading = false,
            error = errorMessage
        )

        viewModel.onPermissionDenied()

        assertEquals(expected, viewModel.uiState.value)
    }
}
