package com.milkyway.milkyway.ui.universe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.FragmentUniverseBinding
import com.milkyway.milkyway.util.DataStore
import com.milkyway.milkyway.util.MarkerDrawer
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UniverseFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentUniverseBinding
    private lateinit var universeBottomSheetBehavior: BottomSheetBehavior<View>
    private val universeViewModel : UniverseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUniverseBinding.inflate(layoutInflater, container, false)
        binding.universeViewModel = universeViewModel
        binding.lifecycleOwner = this
        //뷰모델 연결

        setMap()
        setNicknameText(binding)
        setMarkerData()
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

    private fun setNicknameText(binding : FragmentUniverseBinding) {
        lifecycleScope.launch {
            DataStore(requireContext()).getNickname.collect {
                binding.tvNickname.text = it!!
            }
        }
    }

    private fun setMarkerData() {
        lifecycleScope.launch {
            DataStore(requireContext()).getToken.collect {
                universeViewModel.requestUniverseData(it!!)
            }
        }
    }

    override fun onMapReady(p0: NaverMap) {
        setUiSetting(p0)
        setLightness(p0)
        drawMarkers(p0)
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

    private fun drawMarkers(p0 : NaverMap) {
        universeViewModel.markers.observe(this, Observer{ markers->
            markers?.let {
                MarkerDrawer.apply {
                    universeInit(binding, markers)
                    setUniverseMarkers()
                    setUniverseIcon()
                    drawMarkers(p0)
                }
            }
        })
    }
}