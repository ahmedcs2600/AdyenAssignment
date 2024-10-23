package com.adyen.android.assignment

import com.adyen.android.assignment.api.model.Category
import com.adyen.android.assignment.api.model.GeoCode
import com.adyen.android.assignment.api.model.Icon
import com.adyen.android.assignment.api.model.Location
import com.adyen.android.assignment.api.model.Main
import com.adyen.android.assignment.api.model.Result
import com.adyen.android.assignment.data.datasource.createMockGeocodes
import com.adyen.android.assignment.data.datasource.createMockLocation


object MockResponse {
    fun mockResultWithCategories(): List<Result> {
        return listOf(
            Result(
                categories = listOf(
                    Category(
                        id = "101",
                        name = "Restaurant",
                        icon = Icon(
                            prefix = "https://example.com/icons/abc",
                            suffix = ".png",
                        )
                    )
                ),
                distance = 500,
                description = "A popular spot for brunch with a great selection of wines.",
                location = com.adyen.android.assignment.data.datasource.createMockLocation(),
                name = "GlobalEats",
                timezone = "America/Los_Angeles",
                geocode = com.adyen.android.assignment.data.datasource.createMockGeocodes(),
            )
        )
    }

    fun mockResultWithEmptyCategories(): List<Result> {
        return listOf(
            Result(
                categories = emptyList(),
                distance = 500,
                description = "A popular spot for brunch with a great selection of wines.",
                location = com.adyen.android.assignment.data.datasource.createMockLocation(),
                name = "GlobalEats",
                timezone = "America/Los_Angeles",
                geocode = com.adyen.android.assignment.data.datasource.createMockGeocodes(),
            )
        )
    }

    fun createMockGeocodes(): GeoCode {
        return GeoCode(
            main = Main(latitude = 37.7749, longitude = -122.4194),
        )
    }

    fun createMockLocation(): Location {
        return Location(
            address = "123 Main St",
            country = "US",
            locality = "San Francisco",
            neighbourhood = listOf("Downtown"),
            postcode = "94103",
            region = "CA"
        )
    }
}


