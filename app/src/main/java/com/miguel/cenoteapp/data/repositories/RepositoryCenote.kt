package com.miguel.cenoteapp.data.repositories

import com.miguel.mapsboxexmaple.models.Cenotes
import com.miguel.mapsboxexmaple.models.Legs
import com.miguel.mapsboxexmaple.models.RouteModel

//Repositories
interface RepositoryCenote {
    suspend fun getAllCenotes(): Cenotes?
    suspend fun routes(
        latitudeUser: Double,
        longitudeUser: Double,
        latitudeDestination: Double,
        longitudeDestination: Double
    ): RouteModel?
}