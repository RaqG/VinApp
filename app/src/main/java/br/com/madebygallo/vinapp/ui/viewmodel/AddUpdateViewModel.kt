package br.com.madebygallo.vinapp.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.madebygallo.vinapp.data.VinAppRepository
import br.com.madebygallo.vinapp.model.GrapeVariety
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by RaqG on 22/09/2020.
 */

class AddUpdateViewModel @ViewModelInject constructor(
    private val repository: VinAppRepository,
    @Assisted private val savedStateHandler: SavedStateHandle
) :
    ViewModel() {

    var allGrapesVariety = MutableLiveData<List<GrapeVariety>>()

    fun initListSearch() {
        viewModelScope.launch {
            var list: List<GrapeVariety>
            withContext(Dispatchers.IO) {
                list = repository.getGrapesVariety()
            }
            allGrapesVariety.value = list
        }
    }
}