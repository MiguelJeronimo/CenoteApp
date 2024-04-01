package com.miguel.mapsboxexmaple.models

import com.google.gson.annotations.SerializedName

data class Route(
    val code: String,
    val routes: ArrayList<Routes>,
)

data class Routes(
    val legs: ArrayList<Legs>,
    @SerializedName("weight_name")
    val weightName: String,
    val weight: Float,
    val duration: Float,
    val distance: Float
)

data class Legs(
    val steps: ArrayList<Steps>,
    val summary: String,
    val weight: Float,
    val duration: Float,
    val distance: Float
)

data class Steps(
    val geometry: String,
    val maneuver: Maneuver,
    val mode: String,
    @SerializedName("driving_side")
    val drivingSide: String,
    val name: String,
    val intersections: ArrayList<Intersections>,
    val weight: Float,
    val duration: Float,
    val distance: Float
)
data class Maneuver(
    @SerializedName("bearing_after")
    val bearingAfter: Int,
    @SerializedName("bearing_before")
    val bearingBefore: Int,
    val location: ArrayList<Double>,
    val modifier: String,
    val type: String
)

data class Intersections(
    val out: Int,
    @SerializedName("in")
    val In: Int,
    val location: ArrayList<Double>
)
//data class Waypoints(
//
//)
//API cenotes
data class Cenotes(
    val data: ArrayList<Cenote>
)

data class Cenote(
    val name: String,
    val ubication: Ubication,
    val dataSource: DataSource
)

data class Ubication(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lng")
    val longitude: Double
)

data class DataSource(
    val img: ArrayList<String>
)