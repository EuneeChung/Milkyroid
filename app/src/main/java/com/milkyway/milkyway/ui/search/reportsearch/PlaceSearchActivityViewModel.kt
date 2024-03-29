package com.milkyway.milkyway.ui.search.reportsearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milkyway.milkyway.data.model.PlaceSearchData
import com.milkyway.milkyway.data.remote.response.ResponsePlaceSearch
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceSearchActivityViewModel : ViewModel() {
    private val _recyclerListData = MutableLiveData<MutableList<PlaceSearchData>>()
    val recyclerListData: LiveData<MutableList<PlaceSearchData>>
        get() = _recyclerListData


    fun makeApiCall(toString: String?,token : String) {
        if (toString!="") {
            val search =
                RetrofitBuilder.service.requestPlaceSearch(
                    token = token,
                    query = toString.toString()
                )
            val placeDatas: MutableList<PlaceSearchData> = mutableListOf()
            search.enqueue(
                object : Callback<ResponsePlaceSearch> {
                    override fun onResponse(
                        call: Call<ResponsePlaceSearch>,
                        response: Response<ResponsePlaceSearch>
                    ) {
                        Log.d("typeCheck", "통신성공")
                        if (response.isSuccessful) {
                            placeDatas.clear()

                            for (i in response.body()?.data?.indices!!) {
                                placeDatas.apply {
                                    add(
                                        PlaceSearchData(
                                            cafeName = response.body()!!.data[i].cafeName,
                                            cafeAddress = response.body()!!.data[i].cafeAddress,
                                            longitude = response.body()!!.data[i].longitude,
                                            latitude = response.body()!!.data[i].latitude,
                                            isReported = response.body()!!.data[i].isReported
                                        )
                                    )
                                }
                            }
                            Log.d("print check", placeDatas.toString())
                            _recyclerListData.postValue(placeDatas)
                        }
                    }

                    override fun onFailure(call: Call<ResponsePlaceSearch>, t: Throwable) {
                        Log.d("typeCheck", t.message.toString())
                        _recyclerListData.postValue(null)
                    }
                }
            )
        }
    }
}
