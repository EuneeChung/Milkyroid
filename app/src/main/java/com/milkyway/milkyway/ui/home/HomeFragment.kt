package com.milkyway.milkyway.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.BottomSheetHomeBinding
import com.milkyway.milkyway.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeBottomSheetViewModel:HomeBottomSheetViewModel by viewModels()
    private lateinit var homeBottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeView.setOnClickListener {
            homeBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.bottomsheetHome.vm=homeBottomSheetViewModel
        homeBottomSheetBehavior=BottomSheetBehavior.from(binding.bottomsheetHome.root)

    }

}