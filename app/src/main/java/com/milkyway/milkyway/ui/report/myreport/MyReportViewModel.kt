package com.milkyway.milkyway.ui.report.myreport

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import com.milkyway.milkyway.data.remote.response.MyReport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MyReportViewModel : ViewModel() {

    private val _recyclerListData = MutableLiveData<MyReport>()
    val recyclerListData: LiveData<MyReport>
        get() = _recyclerListData

    // 더미
//    private val _recyclerListData = MutableLiveData<MutableList<DoneData>>()
//    val recyclerListData: LiveData<MutableList<DoneData>>
//        get() = _recyclerListData

//    fun RVCancel() {
//        val cancelDatas = mutableListOf<CancelData>()
//
//        cancelDatas.add(CancelData("현빈스빈스카페", "2021.01.01"))
//        cancelDatas.add(CancelData("현빈스빈스카페", "2021.01.02"))
//        cancelDatas.add(CancelData("현빈스빈스카페", "2021.01.03"))
//        _recyclerListData.postValue(cancelDatas)
//    }


    // 서버 비동기 통신
    fun requestReportData(token : String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val myReport = RetrofitBuilder.service.myReport(token)
            _recyclerListData.postValue(myReport.data)
            Log.d("테스트cancel", "${myReport.data.cancel}")
            Log.d("테스트ing", "${myReport.data.ing}")
            Log.d("테스트done", "${myReport.data.done}")

        } catch (e: HttpException) {
            Log.d("requestmyreport", e.toString())
        }
    }
}