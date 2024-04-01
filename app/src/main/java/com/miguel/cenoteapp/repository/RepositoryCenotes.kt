package com.miguel.mapsboxexmaple.repository

import androidx.lifecycle.MutableLiveData
import com.miguel.mapsboxexmaple.domain.APIServices
import com.miguel.mapsboxexmaple.domain.RetrofitInstances
import com.miguel.mapsboxexmaple.models.Cenotes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryCenotes {

    fun cenotes(_cenotes: MutableLiveData<Cenotes>) {
        val urlBase = "https://migueljeronimo.github.io/"
        val retrofit = RetrofitInstances().getRetrofit(urlBase).create(APIServices::class.java)
        val call = retrofit.cenotes()
        call.enqueue(object: Callback<Cenotes>{
            override fun onResponse(call: Call<Cenotes>, response: Response<Cenotes>) {
                if (response.isSuccessful){
                    val cenotes = response.body()
                    _cenotes.value = cenotes
                }
            }
            override fun onFailure(call: Call<Cenotes>, t: Throwable) {
                _cenotes.value = null
            }
        })
    }



}