package com.milkyway.milkyway.util

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEachIndexed
import com.google.android.material.tabs.TabLayout
import com.milkyway.milkyway.R

fun TextView.setTextBold(isBold: Boolean) {
    this.typeface = ResourcesCompat.getFont(this.context,if(isBold) R.font.roboto_bold else R.font.roboto_regular)
}

fun TabLayout.changeTabsFont(selectPosition: Int) {
    val vg = this.getChildAt(0) as ViewGroup
    val tabsCount = vg.childCount
    for (j in 0 until tabsCount) {
        val vgTab = vg.getChildAt(j) as ViewGroup
        vgTab.forEachIndexed { index, _ ->
            val tabViewChild = vgTab.getChildAt(index)
            if (tabViewChild is TextView) {
                tabViewChild.setTextBold(j == selectPosition)
            }
        }
    }
}
