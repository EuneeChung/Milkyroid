package com.milkyway.milkyway.util

import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.AroundUniverse
import com.milkyway.milkyway.databinding.FragmentUniverseBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

object UniverseMarkerDrawer {

    private val markers = mutableListOf<Marker>()
    private lateinit var universeList : List<AroundUniverse>
    private lateinit var binding : FragmentUniverseBinding

    fun init(initBinding : FragmentUniverseBinding, list : List<AroundUniverse>) {
        binding = initBinding
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
        for(i in universeList.indices) {
            val marker = Marker()
            marker.position = LatLng(universeList[i].latitude, universeList[i].longitude)
            markers.add(marker)
        }
    }

    fun setIcon() {
        for(i in universeList.indices) markers[i].icon = OverlayImage.fromResource(R.drawable.ic_universe_marker)
    }

    fun setClickListener() {
        for(i in 0 until markers.size) {
            markers[i].setOnClickListener {
                markerClick(i)
                true
            }
        }
    }

    private fun markerClick(index : Int) {
        setIcon()
        markers[index].icon = OverlayImage.fromResource(R.drawable.ic_universe_marker_selected)
    }

    fun drawMarkers(map : NaverMap) {
        for(marker in markers) { marker.map = map }
    }
}