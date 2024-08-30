package com.miguel.mapsboxexmaple.models

import org.osmdroid.util.GeoPoint

data class RouteModel(
    var points: ArrayList<GeoPoint>?= null,
    val latitude: Double? = null,
    val longitud: Double?= null,
    var summary: String? = null,
    var weight: Float? = null,
    var distances: Float? = null,
    var duration: Float? = null,
    var navigation: ArrayList<NavigationModel>?= null
)

data class NavigationModel(
    val description: String?=null,
    val icon: Int?= null,
    val distances: Float? = null
)