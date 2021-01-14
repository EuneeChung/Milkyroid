package com.milkyway.milkyway.ui.universe

import android.os.Bundle
import android.util.Log
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
import com.milkyway.milkyway.util.UniverseMarkerDrawer
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UniverseFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentUniverseBinding
    private lateinit var universeBottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var deleteUniverseDialog: ConfirmAlertDialog
    private lateinit var confirmDeleteDialog: ConfirmAlertDialog
    private lateinit var myUniverseListAdapter: UniverseAdapter

    private val universeBottomSheetViewModel: UniverseBottomSheetViewModel by activityViewModels()
    private val universeViewModel : UniverseViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUniverseBinding.inflate(layoutInflater, container, false)
        binding.universeViewModel = universeViewModel
        binding.bottomSheetUniverse.universeViewModel = universeViewModel
        binding.lifecycleOwner = this
        //뷰모델 연결

        setMap()
        setNicknameText(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMyUniverseListView()


        binding.universeView.setOnClickListener {
            universeBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        universeBottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetUniverse.root)


        observeItemClick()
        observeItemDelete()

    }

    private fun setMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.universe_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.universe_map, it).commit()
            }

        mapFragment.getMapAsync(this@UniverseFragment)
    }

    private fun setNicknameText(binding: FragmentUniverseBinding) {
        lifecycleScope.launch {
            DataStore(requireContext()).getNickname.collect {
                binding.tvNickname.text = it!!
                binding.bottomSheetUniverse.tvNoSelectedItems.text = String.format(requireContext().getString(R.string.universe_no_selected_items), it!!)
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
        setLocation(p0)
        setCurrentLocationIcon(p0)
        setCameraMoveListener(p0)
        setLightness(p0)
        setMapClickListener(p0)
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

    private fun setLocation(p0 : NaverMap) {
        val locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        p0.locationSource = locationSource
    }

    private fun setCurrentLocationIcon(p0 : NaverMap) {
        val locationOverlay = p0.locationOverlay
        locationOverlay.isVisible = true
        locationOverlay.icon = OverlayImage.fromResource(R.drawable.ic_current_location_universe)
        locationOverlay.circleRadius = 0

        universeViewModel.compass.observe(this, Observer { compass->
            compass?.let {
                if(compass) {
                    p0.locationTrackingMode = LocationTrackingMode.Face
                    locationOverlay.subIcon = OverlayImage.fromResource(R.drawable.ic_universe_location_face)
                }
                else {
                    p0.locationTrackingMode = LocationTrackingMode.NoFollow
                    locationOverlay.subIcon = OverlayImage.fromResource(R.drawable.ic_universe_location_no_follow)
                }
            }
        })
    }

    private fun setCameraMoveListener(p0 : NaverMap) {
        p0.addOnCameraChangeListener { _, _ ->
            if(p0.locationTrackingMode == LocationTrackingMode.NoFollow) universeViewModel.notCompassIcon()
        }
    }

    private fun setMapClickListener(p0 : NaverMap) {
        p0.setOnMapClickListener { _, _ ->
            universeViewModel.setMapClick()
            UniverseMarkerDrawer.setIcon()
        }
    }

    private fun initMyUniverseListView() {
        myUniverseListAdapter = UniverseAdapter()
        binding.bottomSheetUniverse.rvUniverseBottomSheet.adapter = myUniverseListAdapter

        myUniverseListAdapter.onClickListener =  {
            universeBottomSheetViewModel.setDeleteUniverseClick()
            universeBottomSheetViewModel.clickCafeId.value=myUniverseListAdapter.clickItemCafeId
            //여기서 리싸이클러뷰의 어떤 친구가 클릭 됬는지 알아내야함
            Log.e("deleteUniverse2", universeBottomSheetViewModel.deleteUniverse.value.toString())
        }
    }

    private fun observeItemClick() {
        deleteUniverseDialog= ConfirmAlertDialog(requireContext(),3).create()
        universeBottomSheetViewModel.deleteUniverse.observe(
            viewLifecycleOwner,
            Observer { deleteUniverse ->
                if (deleteUniverse) {
                    Log.e("deleteUniverse1", deleteUniverse.toString())
                    deleteUniverseDialog
                        .show {
                            universeBottomSheetViewModel.setConfirmDeleteClick()
                            universeBottomSheetViewModel.setDeleteUniverseClick()
                        }
                }

            })
    }

    private fun observeItemDelete() {
        confirmDeleteDialog= ConfirmAlertDialog(requireContext(),2).create()
        universeBottomSheetViewModel.confirmDelete.observe(viewLifecycleOwner,
            Observer { confirmDelete ->
                if (confirmDelete) {
                    deleteUniverseDialog.dismiss()
                    confirmDeleteDialog
                        .show{
                            universeBottomSheetViewModel.setConfirmDeleteClick()
                            requestDeleteMyUniverse()
                            myUniverseListAdapter.notifyItemRemoved(myUniverseListAdapter.clickItemPosition)
                            confirmDeleteDialog.dismiss()
                        }
                    Log.e("confirmDelete", confirmDelete.toString())
                }
            })
    }

    private fun drawMarkers(p0 : NaverMap) {
        universeViewModel.markers.observe(this, Observer{ markers->
            markers?.let {
                UniverseMarkerDrawer.apply {
                    init(binding, markers, requireContext())
                    setMarkers()
                    setIcon()
                    setClickListener{
                        universeViewModel.setMarkerClick()
                    }
                    drawMarkers(p0)
                }
            }
        })
    }


    private fun requestDeleteMyUniverse(){
        lifecycleScope.launch {
            DataStore(requireContext()).getToken.collect {
                universeBottomSheetViewModel.requestDeleteUniverse(it!!)
                Log.e("requestDeleteMyUniverse",universeBottomSheetViewModel.clickCafeId.value!!.toString())
            }
        }
    }

    private fun loading() {
        universeViewModel.isLoading()
        binding.imgLoading.playAnimation()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    override fun onResume() {
        super.onResume()
        loading()
        setMarkerData()
        universeViewModel.setMapClick()
    }

}