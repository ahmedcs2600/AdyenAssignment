package com.adyen.android.assignment.data.datasource

import com.adyen.android.assignment.api.model.Category
import com.adyen.android.assignment.api.model.GeoCode
import com.adyen.android.assignment.api.model.Icon
import com.adyen.android.assignment.api.model.Location
import com.adyen.android.assignment.api.model.Main
import com.adyen.android.assignment.api.model.ResponseWrapper
import com.adyen.android.assignment.api.model.Result

fun createMockResponse(): ResponseWrapper {
    return ResponseWrapper(
        results = (1..10).map {
            Result(
                categories = listOf(
                    Category(
                        id = "$it",
                        name = "Restaurant $it",
                        icon = Icon(
                            prefix = "https://cdn.openart.ai/published/v0ps5YJEwbm1ERys5Grr/cSWp5YF6_-6C3_1024",
                            suffix = ".webp",
                        )
                    )
                ),
                distance = 500,
                description = "A popular spot for brunch with a great selection of wines. $it",
                location = createMockLocation(),
                name = "GlobalEats",
                timezone = "America/Los_Angeles",
                geocode = createMockGeocodes(),
            )
        }
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
