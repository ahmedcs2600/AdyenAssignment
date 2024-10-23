package com.adyen.android.assignment.data.datasource

import com.adyen.android.assignment.api.PlacesService
import com.adyen.android.assignment.api.model.Result
import com.adyen.android.assignment.common.Resource
import com.adyen.android.assignment.di.IODispatcher
import com.adyen.android.assignment.common.exceptions.errorhandler.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlinx.coroutines.delay

class PlacesRemoteDataSourceImpl @Inject constructor(
    private val apiService: PlacesService,
    private val errorHandler: ErrorHandler,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : PlacesRemoteDataSource {
    override fun getNearbyPlaces(params: Map<String, String>): Flow<Resource<List<Result>>> {
        return flow<Resource<List<Result>>> {
            delay(1000L)
            val res = createMockResponse().results.orEmpty()
            emit(Resource.Success(res))
           // val response = apiService.getVenueRecommendations(params)
//            if (response.isSuccessful) {
//                emit(Resource.Success(res))
//            } else {
//                emit(Resource.Failed(Throwable(response.message())))
//            }
        }.catch {
            emit(Resource.Failed(errorHandler.handle(it)))
        }.flowOn(ioDispatcher)
    }
}
