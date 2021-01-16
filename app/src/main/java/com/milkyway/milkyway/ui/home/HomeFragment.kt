package com.milkyway.milkyway.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.FragmentHomeBinding
import com.milkyway.milkyway.ui.search.homesearch.CafeSearchActivity
import com.milkyway.milkyway.util.DataStore
import com.milkyway.milkyway.util.Location
import com.milkyway.milkyway.util.MarkerDrawer
import com.milkyway.milkyway.util.Toast
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeBottomSheetBehavior: BottomSheetBehavior<View>
    private val homeViewModel : HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        binding.homeViewModel = homeViewModel
        binding.bottomsheetHome.homeViewModel = homeViewModel
        binding.lifecycleOwner=this

        setMap()
        setNicknameText()
        chipSelect()
        setToastObserver()
        setSearchListener()
        initBottomSheet()
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

    private fun setNicknameText() {
        viewLifecycleOwner.lifecycleScope.launch {
            whenResumed {
                DataStore(requireContext()).getNickname.collect {
                    binding.tvNickname.text =
                        String.format(requireContext().getString(R.string.home_nickname), it!!)
                }
            }
        }
    }

    private fun setMarkerData() {
        viewLifecycleOwner.lifecycleScope.launch {
            whenResumed {
                DataStore(requireContext()).getToken.collect {
                    homeViewModel.requestHomeData(it!!)
                }
            }
        }
    }

    private fun setToastObserver() {
        homeViewModel.toast.observe(viewLifecycleOwner, Observer { toast->
            toast?.let {if(toast) Toast.customToast("원하는 옵션에 맞는 카페가 없어요.\n 다른 옵션을 선택해 볼까요?", requireActivity())}
        })
    }

    private fun setSearchListener() {
        binding.btnSearch.setOnClickListener {
            val intent = Intent(context, CafeSearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initBottomSheet() {
        homeBottomSheetBehavior = BottomSheetBehavior.from(binding.bottomsheetHome.root)
    }

    override fun onMapReady(p0: NaverMap) {
        setUiSetting(p0)
        setLocation(p0)
        setCurrentLocationIcon(p0)
        setCameraMoveListener(p0)
        setMapClickListener(p0)
        drawMarkers(p0)
        locationClickListener(p0)
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

    private fun setMapClickListener(p0 : NaverMap) {
        p0.setOnMapClickListener { _, _ ->
            homeBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
            homeViewModel.setMapClick()
            MarkerDrawer.setIcon()
        }
    }

    private fun drawMarkers(p0 : NaverMap) {
        homeViewModel.markers.observe(this, Observer { markers->
            markers?.let {
                viewLifecycleOwner.lifecycleScope.launch {
                    whenResumed {
                        DataStore(requireContext()).getToken.collect {
                            MarkerDrawer.apply {
                                init(binding, markers, it!!, requireActivity())
                                setMarkers()
                                setIcon()
                                setClickListener {
                                    homeViewModel.setMarkerClick()
                                }
                                drawMarkers(p0)
                            }
                        }
                    }
                }
            }
        })
    }

    private fun locationClickListener(p0 : NaverMap) {
        binding.bottomsheetHome.tvMangwondong.setOnClickListener { bottomSheetSelected(p0,0) }
        binding.bottomsheetHome.tvYeonnamdong.setOnClickListener { bottomSheetSelected(p0,1) }
        binding.bottomsheetHome.tvHannamdong.setOnClickListener { bottomSheetSelected(p0,2) }
        binding.bottomsheetHome.tvSinsadong.setOnClickListener { bottomSheetSelected(p0,3) }
        binding.bottomsheetHome.tvYeoksamdong.setOnClickListener { bottomSheetSelected(p0,4) }
    }

    private fun bottomSheetSelected(p0 : NaverMap, index : Int) {
        homeBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        homeViewModel.chooseLocation(index)
        Location.cameraMove(p0, index)
    }

    private fun chipSelect() {
        val list = mutableListOf<Int>()
        for (index in 0 until binding.chipGroup.childCount) {
            val chip: Chip = binding.chipGroup.getChildAt(index) as Chip
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    chip.setTextAppearanceResource(R.style.HomeChipSelectedTheme)
                    list.add(index + 1)
                } else {
                    chip.setTextAppearanceResource(R.style.HomeChipTheme)
                    list.remove(index + 1)
                }
                loading()
                viewLifecycleOwner.lifecycleScope.launch {
                    whenResumed {
                        DataStore(requireContext()).getToken.collect {
                            if (list.size == 1) homeViewModel.requestCategoryData(it!!, list[0])
                            else if (list.size == 0) homeViewModel.requestHomeData(it!!)
                        }
                    }
                }
            }
        }
    }

    private fun loading() {
        homeViewModel.isLoading()
        binding.imgLoading.playAnimation()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onResume() {
        super.onResume()
        loading()
        setMarkerData()
        homeViewModel.setMapClick()
        homeViewModel.chooseLocation(-1) // RESET
    }
}