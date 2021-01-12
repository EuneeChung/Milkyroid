package com.milkyway.milkyway.ui.universe

import android.content.Context
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
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UniverseFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentUniverseBinding
    private lateinit var universeBottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var deleteUniverseDialog: ConfirmAlertDialog
    private lateinit var confirmDeleteDialog: ConfirmAlertDialog

    private val universeBottomSheetViewModel: UniverseBottomSheetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUniverseBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        //뷰모델 연결

        setMap()
        setNicknameText(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMyUniverseListView(requireContext())


        binding.universeView.setOnClickListener {
            universeBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        universeBottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetUniverse.root)

        observeItemClick(requireContext())
        observeItemDelete(requireContext())

        universeBottomSheetViewModel.setDeleteUniverseClick()
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
            }
        }
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

    private fun initMyUniverseListView(context: Context) {
        val myUniverseListAdapter = MyUniverseListAdapter(context)
        binding.bottomSheetUniverse.rvUniverseBottomSheet.adapter = myUniverseListAdapter
        val listener = View.OnClickListener {
            universeBottomSheetViewModel.setDeleteUniverseClick()
            //여기서 리싸이클러뷰의 어떤 친구가 클릭 됬는지 알아내야함
            Log.e("deleteUniverse2", universeBottomSheetViewModel.deleteUniverse.toString())
        }
        myUniverseListAdapter.onClickListener = listener
        myUniverseListAdapter.data = mutableListOf(
            "혜리는 카페",
            "혜리는 귀염뽀잒 카페",
            "혜리는 카페",
            "혜리는 귀염뽀잒 카페",
            "혜리는 카페",
            "혜리는 귀염뽀잒 카페",
            "혜리는 카페",
            "혜리는 귀염뽀잒 카페",
            "혜리는 카페",
            "혜리는 귀염뽀잒 카페"
        )
        myUniverseListAdapter.notifyDataSetChanged()
    }

    private fun observeItemClick(context: Context) {
        universeBottomSheetViewModel.deleteUniverse.observe(
            viewLifecycleOwner,
            Observer { deleteUniverse ->
                Log.e("deleteUniverse1", deleteUniverse.toString())
                if (deleteUniverse) {
                    deleteUniverseDialog = ConfirmAlertDialog(context = context, type = 3)
                        .show {
                            universeBottomSheetViewModel.setConfirmDeleteClick()
                            universeBottomSheetViewModel.setDeleteUniverseClick()
                        }
                }
                else deleteUniverseDialog.dismiss()
//                Log.e("deleteUniverse",deleteUniverse.toString())
//                Log.e("confirmDelete",universeBottomSheetViewModel.confirmDelete.value.toString())

            })
    }

    private fun observeItemDelete(context: Context) {
        universeBottomSheetViewModel.confirmDelete.observe(viewLifecycleOwner,
            Observer { confirmDelete ->
                if (confirmDelete) {
                    deleteUniverseDialog.dismiss()
                    confirmDeleteDialog = ConfirmAlertDialog(context = context, type = 2)
                        .show{
                            universeBottomSheetViewModel.setConfirmDeleteClick()
                            confirmDeleteDialog.dismiss()
                        }
                    Log.e("confirmDelete", confirmDelete.toString())
                }

            })
    }

}