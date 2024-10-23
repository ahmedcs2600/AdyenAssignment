package com.adyen.android.assignment.common.exceptions.exceptionconverter

import com.adyen.android.assignment.common.exceptions.NetworkException

interface ExceptionConverter {
    fun convert(networkException: NetworkException): String
}