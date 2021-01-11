package com.milkyway.milkyway.ui.home.homesearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milkyway.milkyway.ui.report.search.PlaceSearchData

class CafeSearchViewModel : ViewModel() {
    private val _recyclerListData = MutableLiveData<MutableList<CafeSearchData>>()
    val recyclerListData: LiveData<MutableList<CafeSearchData>>
        get() = _recyclerListData

    fun makeApiCall(toString: String?) {
        //reecyclerListData.postValue(null)
        val cafeDatas: MutableList<CafeSearchData> = mutableListOf()
        cafeDatas.clear()
        //나중에 서버 붙이면 빈 리스트를 보내서 []리스트를 넣으면된다.
        //클라에서 x누를때 clear로 처리해줘도 돼 -> 끊길 경우에 ,.,, ㅋ
        if(toString!="")
        {
            cafeDatas.add(CafeSearchData("dwegegw", "d", true))
            cafeDatas.add(CafeSearchData("안녕클레오파트라세상에서제일가는포테이토칩", "d", true))
            cafeDatas.add(CafeSearchData("d", "d", false))
            cafeDatas.add(CafeSearchData("d", "d", true))
            cafeDatas.add(CafeSearchData("d", "d", false))
            cafeDatas.add(CafeSearchData("d", "d", false))
            cafeDatas.add(CafeSearchData("d", "d", false))
        }
        _recyclerListData.postValue(cafeDatas)
    }


}