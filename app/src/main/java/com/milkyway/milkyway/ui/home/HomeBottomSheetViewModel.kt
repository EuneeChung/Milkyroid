package com.milkyway.milkyway.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeBottomSheetViewModel : ViewModel() {
    private val _isSelectedList: MutableLiveData<MutableList<Boolean>> = MutableLiveData()
    val isSelectedList: LiveData<MutableList<Boolean>>
        get() = _isSelectedList

    fun chooseLocation(index: Int) {
        _isSelectedList.value = MutableList<Boolean>(5) { i-> index == i }
        Log.e("_isSelectedList.value",_isSelectedList.value.toString())
    }

}
