package com.miguel.cenoteapp.domain

import com.miguel.cenoteapp.data.repositories.RepositoryCenote
import com.miguel.mapsboxexmaple.models.Cenotes

class UseCaseCenotes(private val repositoryCenote: RepositoryCenote) {
    //Mandamos a llamar a la funcion del repositorio
    suspend fun invoke(): Cenotes? {
        return repositoryCenote.getAllCenotes()
    }
}