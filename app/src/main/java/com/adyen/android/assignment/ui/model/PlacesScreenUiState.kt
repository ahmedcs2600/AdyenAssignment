package com.adyen.android.assignment.ui.model

import com.adyen.android.assignment.domain.model.Place

data class PlacesScreenUiState(
    val isLoading: Boolean = false,
    val data: List<Place> = emptyList(),
    val error: String? = null,
)
