package com.milkyway.milkyway.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityHomeResultBinding
import com.milkyway.milkyway.ui.home.homesearch.CafeSearchActivity
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.util.startActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource

class HomeResultActivity : AppCompatActivity(), OnMapReadyCallback {
    private val homeViewModel : HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityHomeResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_result)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this

        setLoading(binding)
        setMap()
        setText(binding)
        setBackButton(binding)
    }

    private fun setLoading(binding : ActivityHomeResultBinding) {
        binding.imgLoading.playAnimation()
    }

    private fun setMap() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    private fun setText(binding : ActivityHomeResultBinding) {
        binding.tvSearchResult.text = intent.getStringExtra("cafeName")
        binding.tvCafeName.text = intent.getStringExtra("cafeName")
        binding.tvAddress.text = intent.getStringExtra("cafeAddress")
        binding.tvCafeHour.text = intent.getStringExtra("businessHours")
    }

    private fun setBackButton(binding : ActivityHomeResultBinding) {
        binding.btnBackSearch.setOnClickListener{
            startActivity<CafeSearchActivity>()
        }
        binding.btnBackHome.setOnClickListener{
            startActivity<MainActivity>()
        }
    }

    override fun onMapReady(p0: NaverMap) {
        val position = LatLng(
            intent.getDoubleExtra("latitude", 0.0),
            intent.getDoubleExtra("longitude", 0.0))

        setUiSetting(p0)
        setMarker(p0, position)
        setLocation(p0)
        setCurrentLocationIcon(p0)
        setCameraMoveListener(p0)
        moveCamera(p0, position)
        homeViewModel.isLoadingEnd()
    }

    private fun setUiSetting(p0 : NaverMap) {
        val uiSettings = p0.uiSettings
        uiSettings.isZoomControlEnabled = false // zoom
        uiSettings.isScaleBarEnabled = false // scale
        uiSettings.isCompassEnabled = false // compass
        uiSettings.isLocationButtonEnabled = false // nowLocation
    }

    private fun setMarker(p0: NaverMap, position: LatLng) {
        val marker = Marker()
        marker.position = position
        marker.icon = OverlayImage.fromResource(R.drawable.ic_marker_selected)
        marker.map = p0
    }

    private fun setLocation(p0 : NaverMap) {
        val locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        p0.locationSource = locationSource
    }

    private fun setCurrentLocationIcon(p0 : NaverMap) {
        val locationOverlay = p0.locationOverlay
        locationOverlay.isVisible = true
        locationOverlay.icon = OverlayImage.fromResource(R.drawable.ic_current_location)
        locationOverlay.circleRadius = 0

        homeViewModel.compass.observe(this, Observer { compass->
            compass?.let {
                if(compass) {
                    p0.locationTrackingMode = LocationTrackingMode.Face
                    locationOverlay.subIcon = OverlayImage.fromResource(R.drawable.ic_location_face)
                }
                else {
                    p0.locationTrackingMode = LocationTrackingMode.NoFollow
                    locationOverlay.subIcon = OverlayImage.fromResource(R.drawable.ic_location_no_follow)
                }
            }
        })
    }

    private fun setCameraMoveListener(p0 : NaverMap) {
        p0.addOnCameraChangeListener { _, _ ->
            if(p0.locationTrackingMode == LocationTrackingMode.NoFollow) homeViewModel.notCompassIcon()
        }
    }

    private fun moveCamera(p0 : NaverMap, position: LatLng) {
        val cameraUpdate = CameraUpdate.scrollTo(position)
            .animate(CameraAnimation.Linear)
        p0.defaultCameraAnimationDuration = 1000
        p0.moveCamera(cameraUpdate)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}