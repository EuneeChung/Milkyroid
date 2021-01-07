package com.milkyway.milkyway.ui.report.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CafeDetailViewModel : ViewModel() {
    // MutableLiveData<String>("")초기화
    // 양방향 데이터바인딩
    // -> viewModel에있는데이터를layout에binding
    // -> layout에서입력되는정보를viewModel에반영
    // -> 그래서Twoway(양방향)
    // -> Binding시킬때 "@={viewModel.nickname}"(양방향)
//    val nickname = MutableLiveData<String>("")
}