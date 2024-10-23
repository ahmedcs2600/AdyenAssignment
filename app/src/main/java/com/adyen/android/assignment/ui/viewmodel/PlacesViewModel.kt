package com.adyen.android.assignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.common.Resource
import com.adyen.android.assignment.domain.usecase.NearByPlacesUseCase
import com.adyen.android.assignment.ui.model.PlacesScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val nearByPlacesUseCase: NearByPlacesUseCase,
    private val placesConverter: PlacesConverter,
): ViewModel() {

    private val _uiState = MutableStateFlow(
        PlacesScreenUiState()
    )
    val uiState
        get() = _uiState.asStateFlow()

    fun fetchNearByPlaces(lat: Double, lng: Double) {
        viewModelScope.launch {
            nearByPlacesUseCase(lat, lng)
                .onStart { _uiState.update { it.copy(isLoading = true, error = null) } }
                .collect { res ->
                    when(res) {
                        is Resource.Failed -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = placesConverter.convertToError(res.error)
                                )
                            }
                        }
                        is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    data = res.data
                                )
                            }
                        }
                    }
                }
        }
    }

    fun onGetCurrentLocationFailed() {
        _uiState.update {
            it.copy(
                error = placesConverter.getLocationFailedMessage(),
                data = emptyList(),
                isLoading = false
            )
        }
    }

    fun onPermissionDenied() {
        _uiState.update {
            it.copy(
                error = placesConverter.getPermissionDeniedMessage(),
                data = emptyList(),
                isLoading = false
            )
        }
    }
}
