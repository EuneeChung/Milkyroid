package com.milkyway.milkyway.ui.universe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.FragmentUniverseBinding
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class UniverseFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentUniverseBinding
    private lateinit var universeBottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUniverseBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        //뷰모델 연결

        setMap()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMyUniverseListView()

        binding.universeView.setOnClickListener {
            universeBottomSheetBehavior.state= BottomSheetBehavior.STATE_COLLAPSED
        }

        universeBottomSheetBehavior= BottomSheetBehavior.from(binding.bottomSheetUniverse.root)
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

    private fun initMyUniverseListView() {
        val myUniverseListAdapter = MyUniverseListAdapter()
        binding.bottomSheetUniverse.rvUniverseBottomSheet.adapter = myUniverseListAdapter
        myUniverseListAdapter.data= mutableListOf("혜리는 카페","혜리는 귀염뽀잒 카페")
        myUniverseListAdapter.notifyDataSetChanged()
    }
}