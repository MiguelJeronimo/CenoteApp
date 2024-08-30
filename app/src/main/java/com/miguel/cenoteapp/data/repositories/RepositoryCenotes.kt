package com.miguel.cenoteapp.data.repositories

import com.miguel.cenoteapp.data.network.APIServices
import com.miguel.cenoteapp.utils.Utils
import com.miguel.mapsboxexmaple.models.Cenotes
import com.miguel.mapsboxexmaple.models.NavigationModel
import com.miguel.mapsboxexmaple.models.RouteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint

class RepositoryCenotesImp(private val apiServices: APIServices): RepositoryCenote {
    override suspend fun getAllCenotes(): Cenotes? {
        return withContext(Dispatchers.IO){
            try {
                val provider = apiServices.cenotes()
                println("REPOSITORY: ${provider}")
                if (provider.isSuccessful) {
                    println("REPOSITORY: ${provider.body()}")
                    provider.body()
                } else {
                    null
                }
            } catch (e: Exception){
                println("ERROR: ${e}")
                null
            }
        }
    }

    override suspend fun routes(
        latitudeUser: Double,
        longitudeUser: Double,
        latitudeDestination: Double,
        longitudeDestination: Double,
    ): RouteModel? {
        return withContext(Dispatchers.IO){
            try {
                val routeModel = RouteModel()
                val utils = Utils()
                val routesArray = ArrayList<GeoPoint>()
                val navigationArray = ArrayList<NavigationModel>()
                val provider = apiServices.routes(
                    latitudeUser.toString(),
                    longitudeUser.toString(),
                    latitudeDestination.toString(),
                    longitudeDestination.toString()
                )
                if (provider.isSuccessful){
                    val routes = provider.body()?.routes?.get(0)?.legs!![0]
                    routeModel.apply {
                        summary = routes.summary
                        weight = routes.weight
                        duration = routes.duration
                        distances = routes.distance
                        routes.steps.forEach {
                            val modifier = it.maneuver.modifier
                            //println("MODIFIER: ${it.maneuver.modifier}")
                            val type = it.maneuver.type
                            val street = it.name
                            val references = it.ref
                            val description = utils.direction(modifier, type, street, references)
                            if (description != null){
                                navigationArray.add(
                                    NavigationModel(
                                        description.first,
                                        description.second,
                                        it.distance,
                                    )
                                )
                            }
                            it.intersections.forEach {
                                routesArray.add(GeoPoint(
                                    it.location[1],
                                    it.location[0]
                                ))
                            }
                        }
                        routeModel.navigation = navigationArray
                        routeModel.points = routesArray
                    }
                } else{
                    null
                }
            }    catch (e: Exception){
                println("ERROR: ${e}")
                null
            }
        }
    }
}