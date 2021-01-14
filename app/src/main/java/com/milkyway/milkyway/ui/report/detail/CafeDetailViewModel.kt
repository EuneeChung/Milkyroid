package com.milkyway.milkyway.ui.report.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.model.AddUniverseData
import com.milkyway.milkyway.data.model.CafeInfo
import com.milkyway.milkyway.data.model.CafeMenu
import com.milkyway.milkyway.data.model.RequestCafeId
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CafeDetailViewModel : ViewModel() {
    val cafeId: MutableLiveData<Int> = MutableLiveData(0)
//    val universecount = MutableLiveData<String>("")


    // View에서 관찰할 값(Observe)
    // true-> 유니버스 파랗게, 카운트 하나 증가, false-> 유니버스 회색, 카운트 하나 감소 layout 코드
    private val _isSelected = MutableLiveData<Boolean>()
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

    private val _universeCountData =  MutableLiveData<AddUniverseData>()
    val universeCountData : LiveData<AddUniverseData>
        get() = _universeCountData

    private val _loading = MutableLiveData<Boolean>(true)
    val loading : LiveData<Boolean>
        get() = _loading

//    private val _universeCount = MutableLiveData<Int>()
//    val universeCount : LiveData<Int>
//        get() = _universeCount

    fun isLoading() {
        _loading.value = true
    }

    fun clickCheck() {
        _isSelected.value = !_isSelected.value!!
    }

    fun clickCheck2() {
        _isSelected2.value = !_isSelected2.value!!
    }

    // 서버 비동기 통신
    fun requestDetailData(token : String, cafeId: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val cafeDetail = RetrofitBuilder.service.cafeDetail(token, cafeId)
            _recyclerListData.postValue(cafeDetail.data.menu)
            _cafeInfoData.postValue(cafeDetail.data.cafeInfo)
            Log.d("테스트", "${cafeDetail.data.cafeInfo}")
            _loading.postValue(false)


//            if(cafeDetail.data.cafeInfo.isUniversed == 0){
//                    _isSelected.postValue(false)
//                        Log.d("뷰모델임false", _isSelected.postValue(false).toString())
//                    }
//            if(cafeDetail.data.cafeInfo.isUniversed == 1){
//                        _isSelected.postValue(true)
//                Log.d("뷰모델임true", _isSelected.value.toString())
//                    }

            // .value로 하면 Cannot invoke setValue on a background thread 오류 남
            // mutablelivedata가 setvalue시 메인스레드에서 해야하는데 백그라운드 스레드에서 했을 경우 나오는 에러임
//            tv_detail_cafename.value = cafeDetail.data.cafeInfo.cafeName

            // 뷰모델에서 이렇게 넣지말고 뷰에서 하자-> cafeInfoData에 각 postvalue를 넣는 방식으로 바꿨음 (xml 바인딩 안하고 맨 위 변수 다 없앰)
//            _universeCount.postValue(cafeDetail.data.cafeInfo.universeCount) // 이렇게 백그라운드에서 하도록 postValue로


        } catch (e: HttpException) {
            Log.d("requestdetail", e.toString())
        }
    }

    fun requestDeleteUniverse(token: String, cafeId:Int) = viewModelScope.launch(Dispatchers.IO) {

        try {
            val delete = RetrofitBuilder.service.deleteUniverse(token, cafeId)
            Log.d("response universedel", delete.toString())
            _isSelected.postValue(false)
            _loading.postValue(false)

        } catch (e: HttpException) {
            Log.d("request", e.toString())
            Log.d("requestMessage", e.response().toString())
        }
    }

    fun requestAddUniverse(token: String, cafeId:Int) = viewModelScope.launch(Dispatchers.IO) {

        try {
//            val body = RequestCafeId(cafeId = cafeId)
            val add = RetrofitBuilder.service.addUniverseDetail(token, RequestCafeId(cafeId = cafeId))
            Log.d("response universeadd", add.toString())
            _isSelected.postValue(true)
            _loading.postValue(false)

        } catch (e: HttpException) {
            Log.d("request", e.toString())
            Log.d("requestMessage", e.response().toString())
        }
    }

}