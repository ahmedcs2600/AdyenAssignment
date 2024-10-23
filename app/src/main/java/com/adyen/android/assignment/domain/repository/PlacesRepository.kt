package com.adyen.android.assignment.domain.repository

import com.adyen.android.assignment.common.Resource
import com.adyen.android.assignment.domain.model.Place
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    fun getNearbyPlaces(lat: Double, lng: Double): Flow<Resource<List<Place>>>
}
