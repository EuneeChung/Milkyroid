package com.milkyway.milkyway.util

import com.milkyway.milkyway.data.model.AroundCafe
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker

object MarkerDrawer {

    private val markers = mutableListOf<Marker>()

    fun setMarkers(list : List<AroundCafe>) {
        for(i in list.indices) {
            val marker = Marker()
            marker.position = LatLng(list[i].cafeMapY, list[i].cafeMapX)
            markers.add(marker)
        }
    }

    fun drawMarkers(map : NaverMap) {
        for(element in markers) { element.map = map }
    }
}