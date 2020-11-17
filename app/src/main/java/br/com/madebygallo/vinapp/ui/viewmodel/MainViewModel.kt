package br.com.madebygallo.vinapp.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.madebygallo.vinapp.data.VinAppRepository
import br.com.madebygallo.vinapp.model.GrapeVariety
import br.com.madebygallo.vinapp.model.Wine
import br.com.madebygallo.vinapp.model.WineRegion
import br.com.madebygallo.vinapp.model.WineVariety
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by RaqG on 14/07/2020.
 */

class MainViewModel @ViewModelInject constructor(
    private val repository: VinAppRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var wines = MutableLiveData<List<Wine>>()
    var wineVarieties = MutableLiveData<List<WineVariety>>()
    var grapes = MutableLiveData<List<GrapeVariety>>()
    var region = MutableLiveData<List<WineRegion>>()

    fun getWines() {
        viewModelScope.launch {
            var list: List<Wine>
            withContext(Dispatchers.IO) {
                list = repository.getWines()
            }
            wines.value = list
        }
    }

    fun getWinesVariety() {
        viewModelScope.launch {
            var list: List<WineVariety>
            withContext(Dispatchers.IO) {
                list = repository.getWinesVariety()
            }
            wineVarieties.value = list
        }
    }

    fun getGrapesVariety() {
        viewModelScope.launch {
            var list: List<GrapeVariety>
            withContext(Dispatchers.IO) {
                list = repository.getGrapesVariety()
            }
            grapes.value = list
        }
    }

    fun getWinesRegion() {
        viewModelScope.launch {
            var list: List<WineRegion>
            withContext(Dispatchers.IO) {
                list = repository.getWinesRegion()
            }
            region.value = list
        }
    }

    suspend fun populateGrapeVariety() {
        repository.populateGrapeVariety()
    }

    suspend fun populateWineVariety() {
        repository.populateWineVariety()
    }
}