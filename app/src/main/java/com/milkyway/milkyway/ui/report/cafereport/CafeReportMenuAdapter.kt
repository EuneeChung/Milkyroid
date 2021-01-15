package com.milkyway.milkyway.ui.report.cafereport

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.CafeReportMenuData


class CafeReportMenuAdapter(context : Context) : RecyclerView.Adapter<CafeReportMenuViewHolder>() {

    var itemClick: ItemClick? = null

    var data = mutableListOf<CafeReportMenuData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeReportMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cafe_report_menu,
            parent,
            false
        )
        return CafeReportMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: CafeReportMenuViewHolder, position: Int) {
        holder.onBind(data[position])
        if (itemClick != null) {
            holder.btnViewOption.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    fun clearData(){
        data.clear()
        notifyDataSetChanged()
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
}
