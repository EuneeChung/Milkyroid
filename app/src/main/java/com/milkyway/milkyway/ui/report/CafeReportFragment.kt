package com.milkyway.milkyway.ui.report

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.FragmentCafeReportBinding
import com.milkyway.milkyway.ui.report.search.PlaceSearchActivity

class CafeReportFragment : Fragment() {
    private lateinit var binding: FragmentCafeReportBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cafe_report, container, false)
        binding.cafeReportFragment=this
        binding.lifecycleOwner=this

        return binding.root
    }

    fun onCafeSearchClick(view: View){
        val placesarchIntent = Intent(activity,PlaceSearchActivity::class.java)
        startActivity(placesarchIntent)
    }
}