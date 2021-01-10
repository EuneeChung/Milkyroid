package com.milkyway.milkyway.ui.report.myreport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyReportViewModel : ViewModel() {

    private val _recyclerListData = MutableLiveData<MutableList<CancelData>>()
    val recyclerListData: LiveData<MutableList<CancelData>>
        get() = _recyclerListData

    private val _recyclerListData2 = MutableLiveData<MutableList<IngData>>()
    val recyclerListData2: LiveData<MutableList<IngData>>
        get() = _recyclerListData2

    private val _recyclerListData3 = MutableLiveData<MutableList<DoneData>>()
    val recyclerListData3: LiveData<MutableList<DoneData>>
        get() = _recyclerListData3


    fun RVCancel() {
        val cancelDatas = mutableListOf<CancelData>()

        cancelDatas.add(CancelData("현빈스빈스카페", "2021.01.01"))
        cancelDatas.add(CancelData("현빈스빈스카페", "2021.01.02"))
        cancelDatas.add(CancelData("현빈스빈스카페", "2021.01.03"))
        _recyclerListData.postValue(cancelDatas)
    }

    fun RVIng() {
        val ingDatas = mutableListOf<IngData>()

        ingDatas.add(IngData("혜리의라떼카페", "2021.01.01"))
        ingDatas.add(IngData("혜리의라떼카페", "2021.01.02"))
        ingDatas.add(IngData("혜리의라떼카페", "2021.01.03"))
        _recyclerListData2.postValue(ingDatas)
    }


    fun RVDone() {
        val doneDatas = mutableListOf<DoneData>()

        doneDatas.add(DoneData("2021.01.01","현빈스빈스카페", "서울시 종로구 21-9"))
        doneDatas.add(DoneData("2021.01.01","현빈스빈스카페", "서울시 종로구 21-9(2층)"))
        _recyclerListData3.postValue(doneDatas)
    }
}