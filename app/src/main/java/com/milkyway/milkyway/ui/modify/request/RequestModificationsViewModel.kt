package com.milkyway.milkyway.ui.modify.request

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.remote.request.RequestModify
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RequestModificationsViewModel : ViewModel(){
    val topBarTxt="정보 수정 요청"
    val modifications = MutableLiveData<String>()

    var _isActiveRequest = MutableLiveData<Boolean>(false)
    val isActiveRequest : LiveData<Boolean>
        get() = _isActiveRequest


    fun requestModifications(token : String, reason: String, cafeId:Int) = viewModelScope.launch(
        Dispatchers.IO) {
        try {
            val modifications = RetrofitBuilder.service.modify(token, RequestModify(reason),cafeId)
            Log.e("modify",modifications.toString())
        } catch (e: HttpException) {
            Log.d("request", e.toString())
        }
    }
}