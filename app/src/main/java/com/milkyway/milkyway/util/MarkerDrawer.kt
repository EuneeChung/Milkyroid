package com.milkyway.milkyway.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.remote.response.AroundCafe
import com.milkyway.milkyway.data.remote.request.RequestCafeId
import com.milkyway.milkyway.data.remote.response.ResponseAddUniverse
import com.milkyway.milkyway.data.remote.response.ResponseDeleteUniverse
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import com.milkyway.milkyway.databinding.FragmentHomeBinding
import com.milkyway.milkyway.ui.detail.CafeDetailActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MarkerDrawer {

    private val markers = mutableListOf<Marker>()
    private lateinit var cafeList : List<AroundCafe>
    private lateinit var binding : FragmentHomeBinding
    private lateinit var token : String
    private lateinit var context : Activity

    fun init(initBinding : FragmentHomeBinding, list : List<AroundCafe>, initToken : String, initContext : Activity) {
        binding = initBinding
        cafeList = list
        token = initToken
        context = initContext
        clear()
    }

    private fun clear() {
        for (marker in markers) marker.map = null
        markers.clear()
    }

    fun setMarkers() {
        for (i in cafeList.indices) {
            val marker = Marker()
            marker.position = LatLng(cafeList[i].latitude, cafeList[i].longitude)
            markers.add(marker)
        }
    }

    fun setIcon() {
        for (i in cafeList.indices) {
            if (cafeList[i].isUniversed) markers[i].icon =
                OverlayImage.fromResource(R.drawable.ic_marker_universe)
            else markers[i].icon = OverlayImage.fromResource(R.drawable.ic_marker)
        }
    }

    fun setClickListener(onClick:() -> Unit) {
        for (i in 0 until markers.size) {
            markers[i].setOnClickListener {
                markerClick(i)
                cardData(i)
                onClick()
                true
            }
        }
    }

    fun findData(position: LatLng) : AroundCafe? {
        for(i in cafeList.indices) {
            if (position.latitude == cafeList[i].latitude && position.longitude == cafeList[i].longitude)
                return cafeList[i]
        }
        return null
    }

    fun findIndex(position: LatLng) : Int? {
        for(i in cafeList.indices) {
            if (position.latitude == cafeList[i].latitude && position.longitude == cafeList[i].longitude)
                return i
        }
        return null
    }
    fun updateData(index: Int) {
        cafeList[index].isUniversed = !cafeList[index].isUniversed
        if(cafeList[index].isUniversed) cafeList[index].universeCount += 1
        else cafeList[index].universeCount -= 1
    }

    private fun markerClick(index: Int) {
        setIcon()
        if (cafeList[index].isUniversed)
            markers[index].icon = OverlayImage.fromResource(R.drawable.ic_marker_universe_selected)
        else
            markers[index].icon = OverlayImage.fromResource(R.drawable.ic_marker_selected)
    }

    private fun cardData(index: Int) {
        binding.tvCafeName.text = cafeList[index].cafeName
        binding.tvAddress.text = cafeList[index].cafeAddress
        binding.tvCafeHour.text = String.format(binding.tvCafeHour.context.getString(R.string.home_cafe_hour), cafeList[index].businessHours)

        if(cafeList[index].businessHours!=null) binding.tvCafeHour.visibility = View.VISIBLE
        else binding.tvCafeHour.visibility = View.GONE

        if (cafeList[index].isUniversed) {
            binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe_added)
            binding.btnAddUniverse.setOnClickListener{
                deleteUniverse(index)
            }
            binding.tvLikeCount.setTextColor(getColor(binding.tvLikeCount.context, R.color.blue_3320a6))
            binding.tvLikeCount.typeface = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_bold)
        } else {
            binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe)
            binding.btnAddUniverse.setOnClickListener{
                addUniverse(index)
            }
            binding.tvLikeCount.setTextColor(getColor(binding.tvLikeCount.context, R.color.gray_97))
            binding.tvLikeCount.typeface = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_regular)
        }

        binding.tvLikeCount.text = cafeList[index].universeCount.toString()

        binding.layoutHomeCard.setOnClickListener {
            val intent = Intent(context, CafeDetailActivity::class.java)
            intent.putExtra("cafeId", cafeList[index].id)
            context.startActivity(intent)
        }
    }

    fun drawMarkers(map: NaverMap) {
        for (marker in markers) {
            marker.map = map
        }
    }

    private fun addUniverse(index : Int) {
        val body = RequestCafeId(cafeId = cafeList[index].id)
        val call: Call<ResponseAddUniverse> = RetrofitBuilder.service.addMyUniverseHome(token, body)
        call.enqueue(object : Callback<ResponseAddUniverse> {
            override fun onFailure(call: Call<ResponseAddUniverse>, t: Throwable) {
                Log.d("response", t.localizedMessage!!)
            }
            override fun onResponse(
                call: Call<ResponseAddUniverse>,
                response: Response<ResponseAddUniverse>
            ) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe_added)
                        binding.btnAddUniverse.setOnClickListener{
                            deleteUniverse(index)
                        }
                        binding.tvLikeCount.setTextColor(getColor(binding.tvLikeCount.context, R.color.blue_3320a6))
                        binding.tvLikeCount.typeface = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_bold)
                        binding.tvLikeCount.text = it.data.universeCount.toString()
                        markers[index].icon = OverlayImage.fromResource(R.drawable.ic_marker_universe_selected)
                        cafeList[index].isUniversed = true
                        cafeList[index].universeCount = it.data.universeCount
                        Toast.customToast("나의 유니버스에 추가되었습니다", context)
                    } ?: Log.d("response", response.body().toString())
            }
        })
    }

    private fun deleteUniverse(index : Int) {
        val call: Call<ResponseDeleteUniverse> = RetrofitBuilder.service.deleteUniverseMarker(token, cafeList[index].id)
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
                        binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe)
                        binding.btnAddUniverse.setOnClickListener{
                            addUniverse(index)
                        }
                        binding.tvLikeCount.setTextColor(getColor(binding.tvLikeCount.context, R.color.gray_97))
                        binding.tvLikeCount.typeface = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_bold)
                        binding.tvLikeCount.text = it.data.universeCount.toString()
                        markers[index].icon = OverlayImage.fromResource(R.drawable.ic_marker_selected)
                        cafeList[index].isUniversed = false
                        cafeList[index].universeCount = it.data.universeCount
                        Toast.customToast("나의 유니버스에서 삭제되었습니다", context)
                    } ?: Log.d("response", response.body().toString())
            }
        })
    }
}