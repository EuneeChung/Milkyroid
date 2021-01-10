package com.milkyway.milkyway.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel : ViewModel() {

    private val _compass = MutableLiveData<Boolean>(false)
    val compass : LiveData<Boolean>
        get() = _compass

    private val _card = MutableLiveData<Boolean>(true)
    val card : LiveData<Boolean>
        get() = _card

    fun compassIcon() {
        _compass.value = !_compass.value!!
    }

    fun notCompassIcon() {
        _compass.value = false
    }

    fun setMapClick() {
        _card.value = !_card.value!!
    }

    fun requestHomeData(token : String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val home = RetrofitBuilder.service.home(token)
            Log.d("request", home.toString())
        } catch (e: HttpException) {
            Log.d("request", e.toString())
        }
    }
}