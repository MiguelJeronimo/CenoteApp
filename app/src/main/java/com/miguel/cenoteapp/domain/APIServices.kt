package com.miguel.mapsboxexmaple.domain

import com.miguel.mapsboxexmaple.models.Cenotes
import com.miguel.mapsboxexmaple.models.Route
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//https://router.project-osrm.org/route/v1/driving/-89.619535,20.983867;-90.02454060654473,21.166687163481832?overview=false&alternatives=true&steps=true&hints=;    ruta de paseo 60 a sisal
interface APIServices {
    @GET("driving/{longitudeUser},{latitudeUser};{longitudeDestination},{latitudeDestination}?overview=false&alternatives=true&steps=true&hints=;")
    fun routes(
        @Path("latitudeUser") latitudeUser:String,
        @Path("longitudeUser") longitudeUser:String,
        @Path("latitudeDestination") latitudeDestination:String,
        @Path("longitudeDestination") longitudeDestination:String
    ): Call<Route>

    @GET("rucoydata/cenotesData.json")
    fun cenotes(): Call<Cenotes>
}