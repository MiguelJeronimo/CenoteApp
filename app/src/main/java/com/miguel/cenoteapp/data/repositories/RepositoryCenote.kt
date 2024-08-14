package com.miguel.cenoteapp.data.repositories

import com.miguel.mapsboxexmaple.models.Cenotes

//Repositories
interface RepositoryCenote {
    suspend fun getAllCenotes(): Cenotes?
}