package com.milkyway.milkyway.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.FragmentHomeBinding
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource

class HomeFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentHomeBinding
    private val homeBottomSheetViewModel:HomeBottomSheetViewModel by viewModels()
    private lateinit var homeBottomSheetBehavior: BottomSheetBehavior<View>
    private val homeViewModel : HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner=this

        setMap()
        return binding.root
    }

    private fun setMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this@HomeFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeView.setOnClickListener {
            homeBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.bottomsheetHome.vm=homeBottomSheetViewModel
        homeBottomSheetBehavior=BottomSheetBehavior.from(binding.bottomsheetHome.root)
    }

    override fun onMapReady(p0: NaverMap) {
        setUiSetting(p0)
        setLocation(p0)
        setCurrentLocationIcon(p0)
        setCurrentLocationObserve(p0)
        setCameraMoveListener(p0)
    }

    private fun setUiSetting(p0 : NaverMap) {
        val uiSettings = p0.uiSettings
        uiSettings.isZoomControlEnabled = false // zoom
        uiSettings.isScaleBarEnabled = false // scale
        uiSettings.isCompassEnabled = false // compass
        uiSettings.isLocationButtonEnabled = false // nowLocation
    }

    private fun setLocation(p0 : NaverMap) {
        val locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        p0.locationSource = locationSource
    }

    private fun setCurrentLocationIcon(p0 : NaverMap) {
        val locationOverlay = p0.locationOverlay
        locationOverlay.isVisible = true
        locationOverlay.icon = OverlayImage.fromResource(R.drawable.ic_current_location)
    }

    private fun setCurrentLocationObserve(p0 : NaverMap) {
        homeViewModel.compass.observe(this, Observer { compass->
            compass?.let {
                if(compass) p0.locationTrackingMode = LocationTrackingMode.Face
                else p0.locationTrackingMode = LocationTrackingMode.NoFollow
            }
        })
    }

    private fun setCameraMoveListener(p0 : NaverMap) {
        p0.addOnCameraChangeListener { reason, animated ->
            if(p0.locationTrackingMode == LocationTrackingMode.NoFollow) homeViewModel.notCompassIcon()
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}