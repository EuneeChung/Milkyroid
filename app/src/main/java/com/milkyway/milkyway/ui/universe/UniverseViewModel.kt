package com.milkyway.milkyway.ui.universe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import com.milkyway.milkyway.data.remote.response.AroundUniverse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UniverseViewModel : ViewModel() {

    private val _compass = MutableLiveData<Boolean>(false)
    val compass : LiveData<Boolean>
        get() = _compass

    private val _card = MutableLiveData<Boolean>(false)
    val card : LiveData<Boolean>
        get() = _card

    private val _markers = MutableLiveData<List<AroundUniverse>>()
    val markers : LiveData<List<AroundUniverse>>
        get() = _markers

    private val _loading = MutableLiveData<Boolean>(true)
    val loading : LiveData<Boolean>
        get() = _loading

    private val _deleteUniverse: MutableLiveData<Boolean> = MutableLiveData(false)
    val deleteUniverse: LiveData<Boolean>
        get() = _deleteUniverse

    private val _confirmDelete: MutableLiveData<Boolean> = MutableLiveData(false)
    val confirmDelete: LiveData<Boolean>
        get() = _confirmDelete

    val clickCafeId: MutableLiveData<Int> = MutableLiveData(0)

    fun compassIcon() {
        _compass.value = !_compass.value!!
    }

    fun notCompassIcon() {
        _compass.value = false
    }

    fun setMapClick() {
        _card.value = false
    }

    fun setMarkerClick() {
        _card.value = true
    }

    fun isLoading() {
        _loading.value = true
    }

    fun updateUniverseData(markers : List<AroundUniverse>) {
        _markers.value = markers
    }

    fun setDeleteUniverseClick() {
        _deleteUniverse.value = !_deleteUniverse.value!!
    }

    fun setConfirmDeleteClick() {
        _confirmDelete.value = !_confirmDelete.value!!
    }

    fun requestUniverseData(token : String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val universe = RetrofitBuilder.service.universe(token)
            _loading.postValue(false)
            _markers.postValue(universe.data.aroundUniverse)
        } catch (e: HttpException) {
            Log.d("request", e.toString())
        }
    }

    fun requestDeleteUniverse(token: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val delete = RetrofitBuilder.service.deleteUniverse(token, clickCafeId.value!!)
            Log.d("response universe", delete.toString())
        } catch (e: HttpException) {
            Log.d("request", e.toString())
            Log.d("requestMessage", e.response().toString())
        }
    }
}