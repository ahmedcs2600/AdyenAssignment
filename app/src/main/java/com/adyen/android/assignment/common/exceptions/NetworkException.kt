package com.adyen.android.assignment.common.exceptions

open class NetworkException: Throwable()
class ServerUnreachableException: NetworkException()
class NoNetworkException: NetworkException()
class HttpCallFailureException: NetworkException()
class UnknownException: NetworkException()
