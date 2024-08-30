package com.miguel.cenoteapp

import com.miguel.cenoteapp.data.network.APIServices
import com.miguel.cenoteapp.data.network.RetrofitInstances
import com.miguel.cenoteapp.data.repositories.RepositoryCenote
import com.miguel.cenoteapp.data.repositories.RepositoryCenotesImp
import com.miguel.cenoteapp.domain.usecase.UseCaseCenotes
import com.miguel.cenoteapp.presentation.ViewModels.Factorys.ViewModelMapFactory
import com.miguel.mapsboxexmaple.ViewModels.ViewModelMap
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class DI {

    val appModule = module {
        // Instancia del repositorio
        single <RepositoryCenote>{
            val urlBase = "https://migueljeronimo.github.io/"
            RepositoryCenotesImp(
                RetrofitInstances().getRetrofit(urlBase).create(APIServices::class.java)
            )
        }
        //Instancia del caso de uso
        factory<UseCaseCenotes> {
            UseCaseCenotes(get())
        }

        single<ViewModelMapFactory> { ViewModelMapFactory(get()) }
        //modulo de viewmodel
        viewModel { ViewModelMap(get()) }

    }

}