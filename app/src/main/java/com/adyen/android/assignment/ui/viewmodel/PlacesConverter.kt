package com.adyen.android.assignment.ui.viewmodel

import android.content.Context
import com.adyen.android.assignment.common.exceptions.NetworkException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import  com.adyen.android.assignment.R
import com.adyen.android.assignment.common.exceptions.exceptionconverter.ExceptionConverter

class PlacesConverter @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val converter: ExceptionConverter
) {
    fun convertToError(error: NetworkException): String {
        return converter.convert(error)
    }

    fun getLocationFailedMessage(): String {
        return appContext.getString(R.string.places_screen_page_title)
    }

    fun getPermissionDeniedMessage(): String {
        return appContext.getString(R.string.location_permission_denied)
    }
}
