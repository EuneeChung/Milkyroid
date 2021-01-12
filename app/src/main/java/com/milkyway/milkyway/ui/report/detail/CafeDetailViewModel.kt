package com.milkyway.milkyway.ui.report.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.model.CafeInfo
import com.milkyway.milkyway.data.model.CafeMenu
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CafeDetailViewModel : ViewModel() {
//    val universecount = MutableLiveData<String>("")

    // View에서 관찰할 값(Observe)
    // true-> 유니버스 파랗게, 카운트 하나 증가, false-> 유니버스 회색, 카운트 하나 감소 layout 코드
    private val _isSelected = MutableLiveData<Boolean>(false)
    val isSelected: LiveData<Boolean>
        get() = _isSelected

    private val _isSelected2 = MutableLiveData<Boolean>(false)
    val isSelected2: LiveData<Boolean>
        get() = _isSelected2

    private val _recyclerListData = MutableLiveData<List<CafeMenu>>()
    val recyclerListData : LiveData<List<CafeMenu>>
        get() = _recyclerListData

    private val _cafeInfoData = MutableLiveData<CafeInfo>()
    val cafeInfoData : LiveData<CafeInfo>
        get() = _cafeInfoData

    private val _universeCount = MutableLiveData<Int>()
    val universeCount : LiveData<Int>
        get() = _universeCount


    fun clickCheck() {
        _isSelected.value = !_isSelected.value!!
    }

    fun clickCheck2() {
        _isSelected2.value = !_isSelected2.value!!
    }

    // 서버 비동기 통신
    fun requestDetailData(token : String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val cafeDetail = RetrofitBuilder.service.cafeDetail(token)
            _recyclerListData.postValue(cafeDetail.data.menu)
            _cafeInfoData.postValue(cafeDetail.data.cafeInfo)
            _universeCount.postValue(cafeDetail.data.universeCount)
            Log.d("테스트", "${cafeDetail.data.cafeInfo}")

            // .value로 하면 Cannot invoke setValue on a background thread 오류 남
            // mutablelivedata가 setvalue시 메인스레드에서 해야하는데 백그라운드 스레드에서 했을 경우 나오는 에러임
//            tv_detail_cafename.value = cafeDetail.data.cafeInfo.cafeName
//            Log.d("테스트1", "${tv_detail_cafename.value}")

            // 뷰모델에서 이렇게 넣지말고 뷰에서 하자-> cafeInfoData에 각 postvalue를 넣는 방식으로 바꿨음 (xml 바인딩 안하고 맨 위 변수 다 없앰)
//            tv_detail_cafename.postValue(cafeDetail.data.cafeInfo.cafeName) // 이렇게 백그라운드에서 하도록 postValue로
//            tv_detail_location.postValue(cafeDetail.data.cafeInfo.cafeAddress)
//            tv_detail_businessTime.postValue(cafeDetail.data.cafeInfo.businessHours)
//            tv_detail_call.postValue(cafeDetail.data.cafeInfo.cafePhoneNum)
//            tv_detail_web.postValue(cafeDetail.data.cafeInfo.cafeLink)
//            universecount.postValue(cafeDetail.data.universeCount)



        } catch (e: HttpException) {
            Log.d("requestdetail", e.toString())
        }
    }

}