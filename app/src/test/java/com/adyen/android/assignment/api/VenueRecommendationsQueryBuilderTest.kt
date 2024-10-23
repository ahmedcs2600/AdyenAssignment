package com.adyen.android.assignment.api

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class VenueRecommendationsQueryBuilderTest {

    private lateinit var queryBuilder: VenueRecommendationsQueryBuilder

    @Before
    fun setUp() {
        queryBuilder = VenueRecommendationsQueryBuilder()
    }

    @Test
    fun `putQueryParams should add ll parameter when latitudeLongitude is set`() {
        val latitude = 52.379189
        val longitude = 4.899431
        queryBuilder.setLatitudeLongitude(latitude, longitude)

        val queryParams = mutableMapOf<String, String>()
        queryBuilder.putQueryParams(queryParams)

        assertTrue(queryParams.containsKey("ll"))
        assertEquals("52.379189,4.899431", queryParams["ll"])
    }

    @Test
    fun `putQueryParams should not add ll parameter when latitudeLongitude is not set`() {
        val queryParams = mutableMapOf<String, String>()
        queryBuilder.putQueryParams(queryParams)

        assertFalse(queryParams.containsKey("ll"))
    }
}
