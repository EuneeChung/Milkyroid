package com.milkyway.milkyway.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.milkyway.milkyway.R
import com.milkyway.milkyway.ui.report.cafereport.CafeReportFragment
import com.milkyway.milkyway.ui.report.myreport.MyReportFragment
import com.milkyway.milkyway.util.changeTabsFont

class ReportFragment : Fragment() {
    private lateinit var viewpagerAdapter: ReportViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_report, container, false)

        viewpagerAdapter = ReportViewPagerAdapter(childFragmentManager)
        viewpagerAdapter.fragments = listOf(
            CafeReportFragment(),
            MyReportFragment()
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vptab = getView()?.findViewById<ViewPager>(R.id.vp_report_tab)
        val tab = getView()?.findViewById<TabLayout>(R.id.tab_report)
        vptab?.adapter = viewpagerAdapter
        tab?.setupWithViewPager(vptab)
        tab?.apply {
            getTabAt(0)?.text = "카페 제보"
            getTabAt(1)?.text = "나의 제보"
        }

        val tabLayoutOnPageChangeListener = object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tabItem: TabLayout.Tab?) {}

            override fun onTabUnselected(tabItem: TabLayout.Tab?) {}

            override fun onTabSelected(tabItem: TabLayout.Tab?) {
                tabItem?.position?.let {
                    tab?.changeTabsFont(it)
                }
            }
        }

        tab?.addOnTabSelectedListener(tabLayoutOnPageChangeListener)
        tab?.changeTabsFont(0)
    }
}