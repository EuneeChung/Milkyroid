package com.milkyway.milkyway.ui.home.homesearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milkyway.milkyway.data.model.CafeSearchData
import com.milkyway.milkyway.data.model.CafeSearchResponse
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CafeSearchViewModel : ViewModel() {
    private val _recyclerListData = MutableLiveData<MutableList<CafeSearchData>>()
    val recyclerListData: LiveData<MutableList<CafeSearchData>>
        get() = _recyclerListData

    fun makeApiCall(toString: String?,token : String) {
        if (toString != "") {
            val search =
                RetrofitBuilder.service.requestCafeSearch(
                    token = token,
                    toString = toString.toString())

            val cafeDatas: MutableList<CafeSearchData> = mutableListOf()
            search.enqueue(
                object : Callback<CafeSearchResponse> {
                    override fun onResponse(
                        call: Call<CafeSearchResponse>,
                        response: Response<CafeSearchResponse>
                    ) {
                        Log.d("typeCheck", "통신성공")
                        if (response.isSuccessful) {
                            cafeDatas.clear()
                            for (i in response.body()?.data?.indices!!) {
                                cafeDatas.apply {
                                    add(
                                        CafeSearchData(
                                            cafeName = response.body()!!.data[i].cafeName,
                                            cafeAddress = response.body()!!.data[i].cafeAddress,
                                            longitude = response.body()!!.data[i].longitude,
                                            latitude = response.body()!!.data[i].latitude,
                                            businessHours = response.body()!!.data[i].businessHours
                                        )
                                    )
                                }
                            }

                            Log.d("print check", cafeDatas.toString())
                            _recyclerListData.postValue(cafeDatas)
                        }
                    }
                    override fun onFailure(call: Call<CafeSearchResponse>, t: Throwable) {
                        Log.d("typeCheck", t.message.toString())
                        _recyclerListData.postValue(null)
                    }
                }
            )
        }
    }
}
