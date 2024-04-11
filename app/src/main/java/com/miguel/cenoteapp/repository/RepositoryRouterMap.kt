package com.miguel.mapsboxexmaple.repository

import androidx.lifecycle.MutableLiveData
import com.miguel.cenoteapp.utils.Utils
import com.miguel.mapsboxexmaple.domain.APIServices
import com.miguel.mapsboxexmaple.domain.RetrofitInstances
import com.miguel.mapsboxexmaple.models.NavigationModel
import com.miguel.mapsboxexmaple.models.Route
import com.miguel.mapsboxexmaple.models.RouteModel
import org.osmdroid.util.GeoPoint
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class RepositoryRouterMap {
    val urlBase = "https://router.project-osrm.org/route/v1/"
    fun route(
        latitudeUser: Double,
        longitudeUser: Double,
        latitudeDestination: Double,
        longitudeDestination: Double,
        _routes: MutableLiveData<RouteModel>?
    ) {
        val retrofit = RetrofitInstances().getRetrofit(urlBase).create(APIServices::class.java)
        val call = retrofit.routes(
            latitudeUser.toString(),
            longitudeUser.toString(),
            latitudeDestination.toString(),
            longitudeDestination.toString()
        )
        call.enqueue(object: Callback<Route>{
            val routeModel = RouteModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
            val utils = Utils()
            val routesArray = ArrayList<GeoPoint>()
            val navigationArray = ArrayList<NavigationModel>()
            override fun onResponse(call: Call<Route>, response: Response<Route>) {
                if (response.isSuccessful){
                    val routes = response.body()?.routes?.get(0)?.legs!![0]
                    routeModel.summary = routes.summary
                    routeModel.weight = routes.weight
                    routeModel.duration = routes.duration
                    routeModel.distances = routes.distance
//                    navigationModel.name = routes.steps[0].name )
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
                    _routes!!.value = routeModel
                }
            }
            override fun onFailure(call: Call<Route>, t: Throwable) {
                println("ERRRO: "+t.message)
                _routes?.value = null
            }
        })
    }
}