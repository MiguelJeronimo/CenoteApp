package com.miguel.mapsboxexmaple.models

import org.osmdroid.util.GeoPoint

data class RouteModel(
    var points: ArrayList<GeoPoint>?,
    val latitude: Double?,
    val longitud: Double?
)