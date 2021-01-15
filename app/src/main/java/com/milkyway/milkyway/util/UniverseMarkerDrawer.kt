package com.milkyway.milkyway.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import com.milkyway.milkyway.data.remote.response.AroundUniverse
import com.milkyway.milkyway.data.remote.response.ResponseDeleteUniverse
import com.milkyway.milkyway.databinding.FragmentUniverseBinding
import com.milkyway.milkyway.ui.detail.CafeDetailActivity
import com.milkyway.milkyway.ui.universe.UniverseViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UniverseMarkerDrawer {

    private val markers = mutableListOf<Marker>()
    private lateinit var universeList: MutableList<AroundUniverse>
    private lateinit var binding: FragmentUniverseBinding
    private lateinit var context: Activity
    private lateinit var universeViewModel: UniverseViewModel
    private lateinit var token: String

    fun init(initBinding: FragmentUniverseBinding, list: List<AroundUniverse>, initToken : String, initContext: Activity, viewModel: UniverseViewModel) {
        binding = initBinding
        universeList = list as MutableList<AroundUniverse>
        token = initToken
        context = initContext
        universeViewModel = viewModel
        clear()
    }

    private fun clear() {
        for (marker in markers) marker.map = null
        markers.clear()
    }

    fun setMarkers() {
        for (i in universeList.indices) {
            val marker = Marker()
            marker.position = LatLng(universeList[i].latitude, universeList[i].longitude)
            markers.add(marker)
        }
    }

    fun setIcon() {
        for (i in universeList.indices) markers[i].icon =
            OverlayImage.fromResource(R.drawable.ic_universe_marker)
    }

    fun setClickListener(onClick: () -> Unit) {
        for (i in 0 until markers.size) {
            markers[i].setOnClickListener {
                markerClick(i)
                cardData(i)
                onClick()
                true
            }
        }
    }

    private fun markerClick(index: Int) {
        setIcon()
        markers[index].icon = OverlayImage.fromResource(R.drawable.ic_universe_marker_selected)
    }

    private fun cardData(index: Int) {
        binding.tvCafeName.text = universeList[index].cafeName
        binding.tvAddress.text = universeList[index].cafeAddress
        binding.tvCafeHour.text = String.format(binding.tvCafeHour.context.getString(R.string.home_cafe_hour), universeList[index].businessHours)

        if(universeList[index].businessHours!=null) binding.tvCafeHour.visibility = View.VISIBLE
        else binding.tvCafeHour.visibility = View.GONE

        binding.layoutUniverseCard.setOnClickListener {
            val intent = Intent(context, CafeDetailActivity::class.java)
            intent.putExtra("cafeId", universeList[index].id)
            context.startActivity(intent)
        }

        binding.tvDelete.setOnClickListener{
            deleteDialog(index)
        }
    }

    fun drawMarkers(map: NaverMap) {
        for (marker in markers) {
            marker.map = map
        }
    }

    private fun deleteDialog(index: Int) {
        ConfirmAlertDialog(context, 3).create()
            .show {
                confirmDialog(index)
            }
    }

    private fun confirmDialog(index: Int) {
        ConfirmAlertDialog(context, 2).create()
            .show{
                deleteUniverse(index)
            }
    }

    private fun deleteUniverse(index : Int) {
        val call: Call<ResponseDeleteUniverse> = RetrofitBuilder.service.deleteUniverseMarker(token, universeList[index].id)
        call.enqueue(object : Callback<ResponseDeleteUniverse> {
            override fun onFailure(call: Call<ResponseDeleteUniverse>, t: Throwable) {
                Log.d("response", t.localizedMessage!!)
            }
            override fun onResponse(
                call: Call<ResponseDeleteUniverse>,
                response: Response<ResponseDeleteUniverse>
            ) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        universeList.removeAt(index)
                        universeViewModel.updateUniverseData(universeList)
                        universeViewModel.setMapClick()
                        Toast.customToast("카페가 나의 유니버스를 탈출했어요.", context)
                    } ?: Log.d("response", response.body().toString())
            }
        })
    }
}