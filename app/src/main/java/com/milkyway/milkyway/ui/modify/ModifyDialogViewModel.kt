package com.milkyway.milkyway.ui.modify

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.remote.request.DeleteModify
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ModifyDialogViewModel : ViewModel() {

    val isSelectedList: MutableLiveData<MutableList<Boolean>> =
        MutableLiveData(MutableList(5) { i -> false })

    var _isActive = MutableLiveData<Boolean>(false)
    val isActive: LiveData<Boolean>
        get() = _isActive

    val isDeleteClick: MutableLiveData<Boolean> = MutableLiveData(false)


    fun chooseDeleteReasons(index: Int) {

        isSelectedList.value = MutableList(5) { i -> index == i }
        isSelectedList.value?.forEach {
            if (it) {
                _isActive.value = it
            }
        }
        Log.e("chooseDelete", isSelectedList.value.toString())
    }

    fun requestDeleteLocation(token: String,cafeId:Int) = viewModelScope.launch(Dispatchers.IO) {

        try {
            if (isSelectedList.value != null) {
                Log.e("isSelected",isSelectedList.value?.indexOf(true)!!.toString()!!)
                val delete = RetrofitBuilder.service.delete(
                    token, DeleteModify(isSelectedList.value?.indexOf(true)!!),cafeId =cafeId )
                Log.d("requestMessage", delete.toString())
            }

        } catch (e: HttpException) {
            Log.d("request", e.toString())
            Log.d("requestMessage", e.response().toString())
        }
    }

}