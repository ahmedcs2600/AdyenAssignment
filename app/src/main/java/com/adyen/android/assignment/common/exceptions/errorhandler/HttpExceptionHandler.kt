package com.adyen.android.assignment.common.exceptions.errorhandler

import com.adyen.android.assignment.common.exceptions.HttpCallFailureException
import com.adyen.android.assignment.common.exceptions.NetworkException
import retrofit2.HttpException

class HttpExceptionHandler(private val errorHandler: ErrorHandler): ErrorHandler {
    override fun handle(error: Throwable): NetworkException {
        if(error is HttpException) {
            return HttpCallFailureException()
        }
        return errorHandler.handle(error)
    }
}
