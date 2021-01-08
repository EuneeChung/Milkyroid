package com.milkyway.milkyway.ui.modify.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RequestModificationsViewModel : ViewModel(){
    val topBarTxt="정보 수정 요청"
    val modifications = MutableLiveData<String>("")

    var _isActiveRequest = MutableLiveData<Boolean>(false)
    val isActiveRequest : LiveData<Boolean>
        get() = _isActiveRequest



}