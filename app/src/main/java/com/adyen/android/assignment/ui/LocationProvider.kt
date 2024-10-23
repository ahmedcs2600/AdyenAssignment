package com.adyen.android.assignment.ui

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

object LocationProvider {
    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        context: Context,
        onGetCurrentLocationSuccess: (Double, Double) -> Unit,
        onGetCurrentLocationFailed: (Exception) -> Unit,
        priority: Boolean = true
    ) {
        val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
        else Priority.PRIORITY_BALANCED_POWER_ACCURACY
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationProviderClient.getCurrentLocation(accuracy, CancellationTokenSource().token,)
            .addOnSuccessListener { location ->
            onGetCurrentLocationSuccess(location.latitude, location.longitude)
        }.addOnFailureListener { exception ->
            onGetCurrentLocationFailed(exception)
        }
    }
}