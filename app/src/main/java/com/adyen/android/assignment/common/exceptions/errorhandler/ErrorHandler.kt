package com.adyen.android.assignment.common.exceptions.errorhandler

import com.adyen.android.assignment.common.exceptions.NetworkException

interface ErrorHandler {
    fun handle(error: Throwable): NetworkException
}
