package com.miguel.mapsboxexmaple.ViewModels

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.mapsboxexmaple.models.Cenotes
import com.miguel.mapsboxexmaple.models.RouteModel
import com.miguel.mapsboxexmaple.repository.RepositoryCenotes
import com.miguel.mapsboxexmaple.repository.RepositoryRouterMap

class ViewModelMap: ViewModel() {
    private val routes = RepositoryRouterMap()
    private val cenotesRepository = RepositoryCenotes()
    private val _route = MutableLiveData<RouteModel>()
    val route: MutableLiveData<RouteModel> get() = _route
    private val _positionUser = MutableLiveData<Location?>()
    val positionUser: MutableLiveData<Location?> get() = _positionUser
    private val _cenotes = MutableLiveData<Cenotes>()
    val cenotes: MutableLiveData<Cenotes> get() = _cenotes

    init {
        cenotesRepository.cenotes(_cenotes)
    }

    fun route(
        latitudeUser:Double,
        longitudeUser: Double,
        latitudeDestination:Double,
        longitudeDestination: Double){
        routes.route(
            latitudeUser,
            longitudeUser,
            latitudeDestination,
            longitudeDestination,
            _route
        )
    }

    fun getPoistionUser(location: Location?){
        _positionUser.value = location
    }

    fun cenotes(){
        cenotesRepository.cenotes(_cenotes)
    }
}