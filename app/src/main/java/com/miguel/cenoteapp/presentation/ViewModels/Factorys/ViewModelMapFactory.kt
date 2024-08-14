package com.miguel.cenoteapp.presentation.ViewModels.Factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguel.cenoteapp.domain.UseCaseCenotes
import com.miguel.mapsboxexmaple.ViewModels.ViewModelMap

class ViewModelMapFactory(private val userCaseCenotes: UseCaseCenotes) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelMap::class.java)){
            return ViewModelMap(userCaseCenotes) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}