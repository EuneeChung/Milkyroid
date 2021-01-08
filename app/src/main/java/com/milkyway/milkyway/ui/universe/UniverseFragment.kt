package com.milkyway.milkyway.ui.universe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.milkyway.milkyway.R
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class UniverseFragment : Fragment(), OnMapReadyCallback {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setMap()
        return inflater.inflate(R.layout.fragment_universe, container, false)
    }

    private fun setMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.universe_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.universe_map, it).commit()
            }

        mapFragment.getMapAsync(this@UniverseFragment)
    }

    override fun onMapReady(p0: NaverMap) {
        setUiSetting(p0)
        setLightness(p0)
    }

    private fun setUiSetting(p0: NaverMap) {
        val uiSettings = p0.uiSettings
        uiSettings.isZoomControlEnabled = false // zoom
        uiSettings.isScaleBarEnabled = false // scale
        uiSettings.isCompassEnabled = false // compass
        uiSettings.isLocationButtonEnabled = false // nowLocation
    }

    private fun setLightness(p0: NaverMap) {
        p0.lightness = -0.7f
    }
}