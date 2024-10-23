package com.adyen.android.assignment.api

abstract class PlacesQueryBuilder {

    protected val queryParams = hashMapOf<String, String>()

    fun build(): Map<String, String> {
        putQueryParams(queryParams)
        return queryParams
    }

    abstract fun putQueryParams(queryParams: MutableMap<String, String>)
}
