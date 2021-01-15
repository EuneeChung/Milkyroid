package com.milkyway.milkyway.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.remote.response.AroundCafe
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel : ViewModel() {

    private val _compass = MutableLiveData<Boolean>(false)
    val compass : LiveData<Boolean>
        get() = _compass

    private val _card = MutableLiveData<Boolean>(false)
    val card : LiveData<Boolean>
        get() = _card

    private val _markers = MutableLiveData<List<AroundCafe>>()
    val markers : LiveData<List<AroundCafe>>
        get() = _markers

    private val _isSelectedList: MutableLiveData<MutableList<Boolean>> = MutableLiveData()
    val isSelectedList: LiveData<MutableList<Boolean>>
        get() = _isSelectedList

    private val _loading = MutableLiveData<Boolean>(true)
    val loading : LiveData<Boolean>
        get() = _loading

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

    fun chooseLocation(index: Int) {
        _isSelectedList.value = MutableList(5) { i-> index == i }
    }

    fun requestHomeData(token : String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val home = RetrofitBuilder.service.home(token)
            _loading.postValue(false)
            _markers.postValue(home.data.result)
        } catch (e: HttpException) {
            Log.d("request", e.toString())
        }
    }

    fun requestCategoryData(token: String, categoryId : Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val homeCategory = RetrofitBuilder.service.homeCategory(token, categoryId)
            _loading.postValue(false)
            _markers.postValue(homeCategory.data.result)
        } catch (e: HttpException) {
            Log.d("request", e.toString())
        }
    }
}