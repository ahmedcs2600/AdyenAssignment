package com.adyen.android.assignment.common.exceptions.errorhandler

import com.adyen.android.assignment.common.exceptions.NetworkException
import com.adyen.android.assignment.common.exceptions.NoNetworkException
import java.net.SocketTimeoutException

class SocketTimeoutExceptionHandler(private val errorHandler: ErrorHandler): ErrorHandler {
    override fun handle(error: Throwable): NetworkException {
        if(error is SocketTimeoutException) {
            return NoNetworkException()
        }
        return errorHandler.handle(error)
    }
}
