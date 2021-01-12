package com.milkyway.milkyway.util

import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.AroundCafe
import com.milkyway.milkyway.data.model.AroundUniverse
import com.milkyway.milkyway.databinding.FragmentHomeBinding
import com.milkyway.milkyway.databinding.FragmentUniverseBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

object MarkerDrawer {

    private val markers = mutableListOf<Marker>()
    private lateinit var cafeList : List<AroundCafe>
    private lateinit var binding : FragmentHomeBinding
    private lateinit var universeBinding : FragmentUniverseBinding
    private lateinit var universeList : List<AroundUniverse>

    fun init(initBinding : FragmentHomeBinding, list : List<AroundCafe>) {
        binding = initBinding
        cafeList = list
        clear()
    }

    fun universeInit(initBinding : FragmentUniverseBinding, list : List<AroundUniverse>) {
        universeBinding = initBinding
        universeList = list
        clear()
    }

    private fun clear() {
        for (marker in markers) {
            marker.map = null
        }
        markers.clear()
    }

    fun setMarkers() {
        for(i in cafeList.indices) {
            val marker = Marker()
            marker.position = LatLng(cafeList[i].latitude, cafeList[i].longitude)
            markers.add(marker)
        }
    }

    fun setUniverseMarkers() {
        for(i in universeList.indices) {
            val marker = Marker()
            marker.position = LatLng(universeList[i].latitude, universeList[i].longitude)
            markers.add(marker)
        }
    }

    fun setIcon() {
        for(i in cafeList.indices) {
            if(cafeList[i].isUniversed) markers[i].icon = OverlayImage.fromResource(R.drawable.ic_marker_universe)
            else markers[i].icon = OverlayImage.fromResource(R.drawable.ic_marker)
        }
    }

    fun setUniverseIcon() {
        for(i in universeList.indices) markers[i].icon = OverlayImage.fromResource(R.drawable.ic_universe_marker)
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
        if(cafeList[index].isUniversed) markers[index].icon = OverlayImage.fromResource(R.drawable.ic_marker_universe_selected)
        else markers[index].icon = OverlayImage.fromResource(R.drawable.ic_marker_selected)
    }

    private fun cardData(index : Int) {
        binding.tvCafeName.text = cafeList[index].cafeName
        binding.tvAddress.text = cafeList[index].cafeAddress
        binding.tvCafeHour.text = String.format(binding.tvCafeHour.context.getString(R.string.home_cafe_hour), cafeList[index].businessHours)
        if(cafeList[index].isUniversed) {
            binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe_added)
            binding.tvLikeCount.setTextColor(getColor(binding.tvLikeCount.context, R.color.blue_3320a6))
            val bold = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_bold)
            binding.tvLikeCount.typeface = bold
        }
        else {
            binding.btnAddUniverse.setBackgroundResource(R.drawable.btn_universe)
            binding.tvLikeCount.setTextColor(getColor(binding.tvLikeCount.context, R.color.gray_97))
            val regular = ResourcesCompat.getFont(binding.tvLikeCount.context, R.font.roboto_regular )
            binding.tvLikeCount.typeface = regular
        }
        binding.tvLikeCount.text = cafeList[index].universeCount.toString()
    }

    fun drawMarkers(map : NaverMap) {
        for(marker in markers) { marker.map = map }
    }
}