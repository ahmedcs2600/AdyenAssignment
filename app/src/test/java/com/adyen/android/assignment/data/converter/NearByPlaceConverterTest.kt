package com.adyen.android.assignment.data.converter

import com.adyen.android.assignment.MockResponse
import org.junit.Assert.*
import org.junit.Test
import com.adyen.android.assignment.api.model.Result

class NearByPlaceConverterTest {

    private val subject = NearByPlaceConverter()

    @Test
    fun `convert should correctly map results to places`() {
        val resultList = MockResponse.mockResultWithCategories()

        val places = subject.convert(resultList)

        assertEquals(1, places.size)
        assertEquals("GlobalEats", places[0].name)
        assertEquals("A popular spot for brunch with a great selection of wines.", places[0].description)
        assertEquals("https://example.com/icons/abc.png", places[0].picture)

    }

    @Test
    fun `convert should return null for iconUrl if no categories`() {
        val resultList = MockResponse.mockResultWithEmptyCategories()

        val places = subject.convert(resultList)

        assertEquals(1, places.size)
        assertEquals("GlobalEats", places[0].name)
        assertEquals("A popular spot for brunch with a great selection of wines.", places[0].description)
        assertNull(places[0].picture)
    }

    @Test
    fun `convert should handle empty result list`() {
        val resultList = emptyList<Result>()

        val places = subject.convert(resultList)

        assertEquals(0, places.size)
    }
}