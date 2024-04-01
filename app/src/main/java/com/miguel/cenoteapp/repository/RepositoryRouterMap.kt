package com.miguel.mapsboxexmaple.repository

import androidx.lifecycle.MutableLiveData
import com.miguel.mapsboxexmaple.domain.APIServices
import com.miguel.mapsboxexmaple.domain.RetrofitInstances
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
            val routeModel = RouteModel(null, null, null)
            val routesArray = ArrayList<GeoPoint>()
            override fun onResponse(call: Call<Route>, response: Response<Route>) {
                if (response.isSuccessful){
                    val routes = response.body()?.routes?.get(0)?.legs!![0]
                    routes.steps.forEach {
                        it.intersections.forEach {
                            routesArray.add(GeoPoint(
                                it.location[1],
                                it.location[0]
                            ))
                        }
                    }
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