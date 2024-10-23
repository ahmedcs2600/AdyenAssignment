package com.adyen.android.assignment.ui.viewmodel

import android.content.Context
import com.adyen.android.assignment.common.exceptions.NetworkException
import com.adyen.android.assignment.common.exceptions.exceptionconverter.ExceptionConverter
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import com.adyen.android.assignment.R

@RunWith(MockitoJUnitRunner::class)
class PlacesConverterTest {
    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockExceptionConverter: ExceptionConverter

    @InjectMocks
    private lateinit var placesConverter: PlacesConverter

    @Test
    fun `convertToError should return converted error message`() {
        val networkException = mock(NetworkException::class.java)
        val expectedMessage = "Network error occurred"
        `when`(mockExceptionConverter.convert(networkException)).thenReturn(expectedMessage)

        val result = placesConverter.convertToError(networkException)

        assertEquals(expectedMessage, result)
    }

    @Test
    fun `getLocationFailedMessage should return location failed message`() {
        val expectedMessage = "Location failed"
        `when`(mockContext.getString(R.string.places_screen_page_title)).thenReturn(expectedMessage)

        val result = placesConverter.getLocationFailedMessage()

        assertEquals(expectedMessage, result)
    }

    @Test
    fun `getPermissionDeniedMessage should return permission denied message`() {
        val expectedMessage = "Permission denied"
        `when`(mockContext.getString(R.string.location_permission_denied)).thenReturn(expectedMessage)

        val result = placesConverter.getPermissionDeniedMessage()

        assertEquals(expectedMessage, result)
    }
}
