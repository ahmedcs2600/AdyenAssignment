package com.adyen.android.assignment.data.repository

import com.adyen.android.assignment.domain.model.Place

object PlacesRepositoryMocks {
    fun mockPlaces() = listOf(
        Place(
            name = "name",
            description = "description",
            picture = "picture",
        ),
        Place(
            name = "name",
            description = "description",
            picture = "picture",
        ),
        Place(
            name = "name",
            description = "description",
            picture = "picture",
        )
    )
}