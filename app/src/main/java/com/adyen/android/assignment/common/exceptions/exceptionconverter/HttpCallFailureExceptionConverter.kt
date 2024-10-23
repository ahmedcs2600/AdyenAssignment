package com.adyen.android.assignment.common.exceptions.exceptionconverter

import android.content.Context
import com.adyen.android.assignment.R
import com.adyen.android.assignment.common.exceptions.HttpCallFailureException
import com.adyen.android.assignment.common.exceptions.NetworkException
import dagger.hilt.android.qualifiers.ApplicationContext

class HttpCallFailureExceptionConverter(private val converter: ExceptionConverter, @ApplicationContext private val appContext: Context): ExceptionConverter {
    override fun convert(networkException: NetworkException): String {
        if(networkException is HttpCallFailureException) {
            return appContext.getString(R.string.http_call_exception)
        }
        return converter.convert(networkException)
    }
}
