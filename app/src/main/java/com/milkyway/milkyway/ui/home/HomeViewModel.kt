package com.milkyway.milkyway.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
}