package com.milkyway.milkyway.ui.universe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.model.AroundUniverse
import com.milkyway.milkyway.data.remote.RetrofitBuilder
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

    fun requestUniverseData(token : String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val universe = RetrofitBuilder.service.universe(token)
            _markers.postValue(universe.data.aroundUniverse)
        } catch (e: HttpException) {
            Log.d("request", e.toString())
        }
    }
}