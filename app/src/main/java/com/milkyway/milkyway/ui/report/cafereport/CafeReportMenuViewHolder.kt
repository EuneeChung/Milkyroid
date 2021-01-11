package com.milkyway.milkyway.ui.report.cafereport

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R


class CafeReportMenuViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val btnViewOption : ImageView = itemView.findViewById(R.id.btn_cafe_report_edit_delete)
    private val cafeMenuName: TextView = itemView.findViewById(R.id.tv_cafe_report_menu_name)
    private val cafeMenuPrice: TextView = itemView.findViewById(R.id.tv_cafe_report_money)
    private val cafeMenuCategory: TextView = itemView.findViewById(R.id.tv_cafe_report_select_milk1)

    fun onBind(data: CafeReportMenuData) {
        cafeMenuName.text = data.cafeMenuName
        cafeMenuPrice.text = data.cafeMenuPrice
        cafeMenuCategory.text = data.cafeMenuCategory
    }

}

