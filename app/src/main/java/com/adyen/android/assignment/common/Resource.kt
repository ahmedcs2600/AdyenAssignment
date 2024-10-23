package com.adyen.android.assignment.common

import com.adyen.android.assignment.common.exceptions.NetworkException

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failed<T>(val error: NetworkException) : Resource<T>()
}