package com.adyen.android.assignment.common.exceptions.exceptionconverter

import android.content.Context
import com.adyen.android.assignment.R
import com.adyen.android.assignment.common.exceptions.HttpCallFailureException
import com.adyen.android.assignment.common.exceptions.NetworkException
import com.adyen.android.assignment.common.exceptions.NoNetworkException
import dagger.hilt.android.qualifiers.ApplicationContext

class NoNetworkExceptionConverter(private val converter: ExceptionConverter, @ApplicationContext private val appContext: Context): ExceptionConverter {
    override fun convert(networkException: NetworkException): String {
        if(networkException is NoNetworkException) {
            return appContext.getString(R.string.no_network_error)
        }
        return converter.convert(networkException)
    }
}
