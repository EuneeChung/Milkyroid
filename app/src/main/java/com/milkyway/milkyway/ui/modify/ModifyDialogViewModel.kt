package com.milkyway.milkyway.ui.modify

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.model.DeleteModify
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ModifyDialogViewModel : ViewModel() {

    val isSelectedList: MutableLiveData<MutableList<Boolean>> =
        MutableLiveData(MutableList(5) { i -> false})

    var _isActive = MutableLiveData<Boolean>(false)
    val isActive: LiveData<Boolean>
        get() = _isActive

    val isDeleteClick :MutableLiveData<Boolean> = MutableLiveData(false)

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

    fun requestDeleteLocation(token : String,reason:Int,cafeId:Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val delete = RetrofitBuilder.service.delete(token,DeleteModify(reason),cafeId)
            Log.e("delete",delete.message)
        } catch (e: HttpException) {
            Log.d("request", e.toString())
        }
    }

}