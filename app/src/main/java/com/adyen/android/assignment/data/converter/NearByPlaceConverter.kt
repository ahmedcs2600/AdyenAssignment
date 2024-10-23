package com.adyen.android.assignment.data.converter

import com.adyen.android.assignment.api.model.Result
import com.adyen.android.assignment.domain.model.Place
import javax.inject.Inject

class NearByPlaceConverter @Inject constructor() {

    fun convert(results: List<Result>): List<Place> {
        return results.map { convert(it) }
    }

    private fun convert(result: Result): Place {
        return Place(
            result.name,
            result.description,
            result.categories.firstOrNull()?.let {
                it.icon.prefix.plus(it.icon.suffix)
            },
        )
    }
}