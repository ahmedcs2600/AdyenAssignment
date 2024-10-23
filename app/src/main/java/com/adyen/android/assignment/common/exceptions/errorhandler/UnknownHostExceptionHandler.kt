package com.adyen.android.assignment.common.exceptions.errorhandler

import com.adyen.android.assignment.common.exceptions.NetworkException
import com.adyen.android.assignment.common.exceptions.ServerUnreachableException
import java.net.SocketTimeoutException

class UnknownHostExceptionHandler(private val errorHandler: ErrorHandler): ErrorHandler {
    override fun handle(error: Throwable): NetworkException {
        if(error is SocketTimeoutException) {
            return ServerUnreachableException()
        }
        return errorHandler.handle(error)
    }
}
