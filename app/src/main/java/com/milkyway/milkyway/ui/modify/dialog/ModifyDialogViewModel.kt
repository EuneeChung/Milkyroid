package com.milkyway.milkyway.ui.modify.dialog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModifyDialogViewModel : ViewModel() {

    val isSelectedList: MutableLiveData<MutableList<Boolean>> =
        MutableLiveData(MutableList(5) { i -> false})

    var _isActive = MutableLiveData<Boolean>(false)
    val isActive: LiveData<Boolean>
        get() = _isActive

    fun chooseDeleteReasons(index: Int) {
        val list = isSelectedList.value
        list?.set(index, list?.get(index))
        isSelectedList.value = list

        list?.forEach {
            if (it) {
                _isActive.value = it
            }
        }

        Log.e("chooseDelete", isSelectedList.value.toString())
    }

}