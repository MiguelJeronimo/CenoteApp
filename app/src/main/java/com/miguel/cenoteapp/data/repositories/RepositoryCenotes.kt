package com.miguel.cenoteapp.data.repositories

import androidx.lifecycle.MutableLiveData
import com.miguel.cenoteapp.data.network.APIServices
import com.miguel.cenoteapp.data.network.RetrofitInstances
import com.miguel.mapsboxexmaple.models.Cenotes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryCenotesImp(private val apiServices: APIServices): RepositoryCenote {

/*    fun cenotes(_cenotes: MutableLiveData<Cenotes>) {
        val urlBase = "https://migueljeronimo.github.io/"
        val retrofit = RetrofitInstances().getRetrofit(urlBase).create(APIServices::class.java)
        val call = retrofit.cenotes()
        call.enqueue(object : Callback<Cenotes> {
            override fun onResponse(call: Call<Cenotes>, response: Response<Cenotes>) {
                if (response.isSuccessful) {
                    val cenotes = response.body()
                    _cenotes.value = cenotes
                }
            }

            override fun onFailure(call: Call<Cenotes>, t: Throwable) {
                _cenotes.value = null
            }
        })
    }*/

    override suspend fun getAllCenotes(): Cenotes? {
        return withContext(Dispatchers.IO){
            try {
                val provider = apiServices.cenotes().execute()
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
}