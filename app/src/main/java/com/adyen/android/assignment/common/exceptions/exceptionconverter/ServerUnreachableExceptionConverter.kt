package com.adyen.android.assignment.common.exceptions.exceptionconverter

import android.content.Context
import com.adyen.android.assignment.R
import com.adyen.android.assignment.common.exceptions.NetworkException
import com.adyen.android.assignment.common.exceptions.ServerUnreachableException
import dagger.hilt.android.qualifiers.ApplicationContext

class ServerUnreachableExceptionConverter(private val converter: ExceptionConverter, @ApplicationContext private val appContext: Context): ExceptionConverter {
    override fun convert(networkException: NetworkException): String {
        if(networkException is ServerUnreachableException) {
            return appContext.getString(R.string.server_unreachable_error)
        }
        return converter.convert(networkException)
    }
}
