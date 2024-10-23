package com.adyen.android.assignment.common.exceptions.errorhandler

import com.adyen.android.assignment.common.exceptions.NetworkException
import com.adyen.android.assignment.common.exceptions.UnknownException

class UnknownExceptionHandler: ErrorHandler {
    override fun handle(error: Throwable): NetworkException {
        return UnknownException()
    }
}
