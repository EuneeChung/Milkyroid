package com.milkyway.milkyway.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.milkyway.milkyway.R


class CafeReportFragment : Fragment() {
    private val honeyTipChipList = mutableListOf<Chip>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_cafe_report, container, false)
    }
}