package com.miguel.mapsboxexmaple.ViewModels

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.mapsboxexmaple.models.Cenotes
import com.miguel.mapsboxexmaple.models.RouteModel
import com.miguel.cenoteapp.domain.usecase.UseCaseCenotes
import kotlinx.coroutines.launch

class ViewModelMap(private val userCaseCenotes: UseCaseCenotes): ViewModel() {
    private val _route = MutableLiveData<RouteModel?>()
    val route: MutableLiveData<RouteModel?> get() = _route
    private val _positionUser = MutableLiveData<Location?>()
    val positionUser: MutableLiveData<Location?> get() = _positionUser
    private val _cenotes = MutableLiveData<Cenotes>()
    val cenotes: MutableLiveData<Cenotes> get() = _cenotes

    init {
        viewModelScope.launch {
            println("DATA: "+userCaseCenotes.invoke())
            _cenotes.value = userCaseCenotes.invoke()
        }
    }

    fun route(
        latitudeUser:Double,
        longitudeUser: Double,
        latitudeDestination:Double,
        longitudeDestination: Double){
        viewModelScope.launch {
            val routes = userCaseCenotes.invokeRoutes(
                latitudeUser,
                longitudeUser,
                latitudeDestination,
                longitudeDestination
            )
            _route.value = routes
        }
    }

    fun getPoistionUser(location: Location?){
        _positionUser.value = location
    }

    fun cenotes(){
        viewModelScope.launch {
           // println("DATA: "+userCaseCenotes.invoke())
           // _cenotes.value = userCaseCenotes.invoke()
        }
    }
}