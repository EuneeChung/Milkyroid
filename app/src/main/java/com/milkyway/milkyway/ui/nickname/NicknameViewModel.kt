package com.milkyway.milkyway.ui.nickname

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NicknameViewModel : ViewModel() {
    val nickname = MutableLiveData<String>("")
}