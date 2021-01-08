package com.milkyway.milkyway.ui.report.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaceSearchActivityViewModel : ViewModel() {
    private val _recyclerListData = MutableLiveData<MutableList<PlaceSearchData>>()
    val recyclerListData: LiveData<MutableList<PlaceSearchData>>
        get() = _recyclerListData

    fun makeApiCall(toString: String?) {
        //reecyclerListData.postValue(null)
        val placeDatas: MutableList<PlaceSearchData> = mutableListOf()
        placeDatas.clear()
        //나중에 서버 붙이면 빈 리스트를 보내서 []리스트를 넣으면된다.
        //클라에서 x누를때 clear로 처리해줘도 돼 -> 끊길 경우에 ,.,, ㅋ
        if(toString!="")
        {
            placeDatas.add(PlaceSearchData("dwegegw", "d", true))
            placeDatas.add(PlaceSearchData("안녕클레오파트라세상에서제일가는포테이토칩", "d", true))
            placeDatas.add(PlaceSearchData("d", "d", false))
            placeDatas.add(PlaceSearchData("d", "d", true))
            placeDatas.add(PlaceSearchData("d", "d", false))
            placeDatas.add(PlaceSearchData("d", "d", false))
            placeDatas.add(PlaceSearchData("d", "d", false))
        }
        _recyclerListData.postValue(placeDatas)
    }
}