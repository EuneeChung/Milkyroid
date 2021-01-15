package com.milkyway.milkyway.util

import android.content.Context
import android.content.Intent
import android.view.View
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.remote.response.AroundUniverse
import com.milkyway.milkyway.databinding.FragmentUniverseBinding
import com.milkyway.milkyway.ui.report.detail.CafeDetailActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

object UniverseMarkerDrawer {

    private val markers = mutableListOf<Marker>()
    private lateinit var universeList: List<AroundUniverse>
    private lateinit var binding: FragmentUniverseBinding
    private lateinit var context : Context


    fun init(initBinding: FragmentUniverseBinding, list: List<AroundUniverse>, initContext: Context) {
        binding = initBinding
        universeList = list
        context = initContext
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
    }

    fun drawMarkers(map: NaverMap) {
        for (marker in markers) {
            marker.map = map
        }
    }
}