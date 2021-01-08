package com.milkyway.milkyway.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.model.RequestSign
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SplashViewModel : ViewModel() {

    private val _isSignIn = MutableLiveData<Boolean>()
    val isSignIn : LiveData<Boolean>
        get() = _isSignIn

    fun signIn(uuid : String, nickname : String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val signIn = RetrofitBuilder.service.signIn(RequestSign(uuid = uuid, nickName = nickname))
            if (signIn.status == 200) _isSignIn.postValue(true)
        } catch (e: HttpException) {
            _isSignIn.postValue(false)
        }
    }
}