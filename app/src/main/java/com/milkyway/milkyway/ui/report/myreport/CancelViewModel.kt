package com.milkyway.milkyway.ui.report.myreport

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CancelViewModel : ViewModel () {
    private val _deleteCancel: MutableLiveData<Boolean> = MutableLiveData(false)
    val deleteCancel: LiveData<Boolean>
        get() = _deleteCancel

//    private val _confirmDelete: MutableLiveData<Boolean> = MutableLiveData(false)
//    val confirmDelete: LiveData<Boolean>
//        get() = _confirmDelete

    val clickCafeId: MutableLiveData<Int> = MutableLiveData(0)

    fun setDeleteCancelClick() {
        _deleteCancel.value = !_deleteCancel.value!!
    }

    fun requestDeleteCancel(token: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            Log.e("클릭카페아이디", clickCafeId.value.toString())
            val delete = RetrofitBuilder.service.deleteCancel(token, clickCafeId.value!!)
            Log.d("response cancel", delete.toString())

        } catch (e: HttpException) {
            Log.d("requestcanceldelete", e.response().toString())
        }
    }
}