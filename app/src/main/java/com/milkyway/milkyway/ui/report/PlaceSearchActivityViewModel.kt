package com.milkyway.milkyway.ui.report

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
        placeDatas.add(PlaceSearchData("dwegegw", "d", true))
        placeDatas.add(PlaceSearchData("d", "d", true))
        placeDatas.add(PlaceSearchData("d", "d", false))
        placeDatas.add(PlaceSearchData("d", "d", true))
        placeDatas.add(PlaceSearchData("d", "d", false))
        placeDatas.add(PlaceSearchData("d", "d", false))
        placeDatas.add(PlaceSearchData("d", "d", false))
        _recyclerListData.postValue(placeDatas)
    }
}