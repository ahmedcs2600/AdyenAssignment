package com.adyen.android.assignment.common.exceptions.exceptionconverter

import android.content.Context
import com.adyen.android.assignment.R
import com.adyen.android.assignment.common.exceptions.NetworkException
import dagger.hilt.android.qualifiers.ApplicationContext

class UnknownExceptionConverter(@ApplicationContext private val appContext: Context): ExceptionConverter {
    override fun convert(networkException: NetworkException): String {
        return appContext.getString(R.string.server_unreachable_error)
    }
}
