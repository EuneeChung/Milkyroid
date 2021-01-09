package com.milkyway.milkyway.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _compass = MutableLiveData<Boolean>(false)
    val compass : LiveData<Boolean>
        get() = _compass

    fun compassIcon() {
        _compass.value = !_compass.value!!
    }

    fun notCompassIcon() {
        _compass.value = false
    }
}