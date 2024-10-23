package com.adyen.android.assignment.domain.usecase

import com.adyen.android.assignment.common.Resource
import com.adyen.android.assignment.domain.model.Place
import com.adyen.android.assignment.domain.repository.PlacesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NearByPlacesUseCase @Inject constructor(private val placesRepository: PlacesRepository) {
    operator fun invoke(lat: Double, lng: Double): Flow<Resource<List<Place>>> {
        return placesRepository.getNearbyPlaces(lat, lng)
    }
}
