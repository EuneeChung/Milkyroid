package com.milkyway.milkyway.util

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap

object Location {
    fun cameraMove(p0 : NaverMap, location : Int) {
        var move = LatLng(0.0, 0.0)
        when(location) {
            0 -> move = LatLng(37.557852, 126.907507) //mangwon
            1 -> move = LatLng(37.562680, 126.921358) //yeonnam
            2 -> move = LatLng(37.536427, 127.005132) //hannam
            3 -> move = LatLng(37.523733, 127.021235) //sinsa
            4 -> move = LatLng(37.500667, 127.038390) //yeoksam
        }
        val cameraUpdate = CameraUpdate.scrollTo(move)
            .animate(CameraAnimation.Linear)
        p0.defaultCameraAnimationDuration = 1000
        p0.moveCamera(cameraUpdate)
    }
}