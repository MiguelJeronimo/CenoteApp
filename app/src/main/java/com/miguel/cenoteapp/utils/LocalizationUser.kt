package com.miguel.mapsboxexmaple.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

class LocalizationUser {

    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getUserLocation(context: Context): Location? {
        val fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        return suspendCancellableCoroutine {cont ->
            fusedLocationProviderClient.lastLocation.apply {
                if (isComplete){
                    if (isSuccessful){
                        cont.resume(result){}
                    } else{
                        cont.resume(null){}
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it){}
                    println("correcto")
                }
                addOnFailureListener{
                    cont.resume(null){}
                    println("Fallo")
                }
                addOnCanceledListener {
                    cont.resume(null){}
                    println("Cancelado")
                }
            }
        }
    }
}