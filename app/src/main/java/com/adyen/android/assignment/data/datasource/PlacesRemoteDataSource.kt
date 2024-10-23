package com.adyen.android.assignment.data.datasource

import com.adyen.android.assignment.api.model.Result
import com.adyen.android.assignment.common.Resource
import kotlinx.coroutines.flow.Flow

interface PlacesRemoteDataSource {
    fun getNearbyPlaces(params: Map<String, String>): Flow<Resource<List<Result>>>
}
