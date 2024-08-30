package com.miguel.cenoteapp.domain.usecase

import com.miguel.cenoteapp.data.repositories.RepositoryCenote
import com.miguel.mapsboxexmaple.models.Cenotes
import com.miguel.mapsboxexmaple.models.RouteModel

class UseCaseCenotes(private val repositoryCenote: RepositoryCenote) {
    //Mandamos a llamar a la funcion del repositorio
    suspend fun invoke(): Cenotes? {
        return repositoryCenote.getAllCenotes()
    }

    suspend fun invokeRoutes(
        latitudeUser: Double,
        longitudeUser: Double,
        latitudeDestination: Double,
        longitudeDestination: Double
    ): RouteModel? {
        return repositoryCenote.routes(
            latitudeUser,
            longitudeUser,
            latitudeDestination,
            longitudeDestination
        )
    }
}