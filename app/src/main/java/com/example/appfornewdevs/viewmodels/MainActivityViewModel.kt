package com.example.appfornewdevs.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.appfornewdevs.MainApplication
import com.example.appfornewdevs.api.Resource
import com.example.appfornewdevs.models.RegionModel
import com.example.appfornewdevs.util.toLiveData
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private fun getApiServices() = getApplication<MainApplication>().appServices

    fun loadLanguages(): LiveData<Resource<List<RegionModel>>> =
        getApiServices().getEuropeRegions()
            .subscribeOn(Schedulers.io())
            .map { region ->
                region.asSequence()
                    .filter { it.region == RegionModel.REGION_EUROPE }
                    .sortedBy { it.name }
                    .toList()
            }
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .toLiveData()
}