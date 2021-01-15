package com.milkyway.milkyway.ui.nickname

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import com.milkyway.milkyway.data.remote.request.RequestChangeNickname
import com.milkyway.milkyway.data.remote.request.RequestSign
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.regex.Pattern

class NicknameViewModel : ViewModel() {
    val nickname = MutableLiveData<String>("")

    private val _isSignUp = MutableLiveData<Boolean>()
    val isSignUp : LiveData<Boolean>
        get() = _isSignUp

    private val _token = MutableLiveData<String>()
    val token : LiveData<String>
        get() = _token

    fun signUp(uuid : String) = viewModelScope.launch(Dispatchers.IO) {
        if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname.value!!)) {
            try {
                val signUp = RetrofitBuilder.service.signUp(RequestSign(uuid = uuid, nickName = nickname.value!!))
                if (signUp.status == 200) {
                    _token.postValue(signUp.data.refreshToken)
                    _isSignUp.postValue(true)
                }
            } catch (e: HttpException) {
                _isSignUp.postValue(false)
            }
        }
    }

    fun changeNickname(token : String, newNickName : String) = viewModelScope.launch(Dispatchers.IO) {
        if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname.value!!)) {
            try {
                val changeNickname = RetrofitBuilder.service.changeNickname(token, RequestChangeNickname(newNickName = newNickName))
                if (changeNickname.status == 200) {
                    Log.d("response nickname", changeNickname.toString())
                    _isSignUp.postValue(true)
                }
            } catch (e: HttpException) {
                _isSignUp.postValue(false)
            }
        }
    }
}