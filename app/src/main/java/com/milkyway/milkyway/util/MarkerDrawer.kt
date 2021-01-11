package com.milkyway.milkyway.util

import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.AroundCafe
import com.milkyway.milkyway.databinding.FragmentHomeBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

object MarkerDrawer {

    private val markers = mutableListOf<Marker>()
    private lateinit var cafeList : List<AroundCafe>
    private lateinit var binding : FragmentHomeBinding

    fun init(initBinding : FragmentHomeBinding, list : List<AroundCafe>) {
        binding = initBinding
        cafeList = list
    }

    fun setMarkers() {
        for(i in cafeList.indices) {
            val marker = Marker()
            marker.position = LatLng(cafeList[i].cafeMapY, cafeList[i].cafeMapX)
            markers.add(marker)
        }
    }

    fun setIcon() {
        for(marker in markers) { marker.icon = OverlayImage.fromResource(R.drawable.ic_marker) }
    }

    fun setClickListener(onClick : () -> Unit) {
        for(i in 0 until markers.size) {
            markers[i].setOnClickListener {
                markerClick(i)
                cardData(i)
                onClick()
                true
            }
        }
    }

    private fun markerClick(index : Int) {
        setIcon()
        markers[index].icon = OverlayImage.fromResource(R.drawable.ic_marker_selected)
    }

    private fun cardData(index : Int) {
        binding.tvCafeName.text = cafeList[index].cafeName
        binding.tvAddress.text = cafeList[index].cafeAddress
        binding.tvCafeHour.text = String.format(binding.tvCafeHour.context.getString(R.string.home_cafe_hour), cafeList[index].businessHours)
        binding.tvLikeCount.text = 123.toString()
    }

    fun drawMarkers(map : NaverMap) {
        for(marker in markers) { marker.map = map }
    }
}