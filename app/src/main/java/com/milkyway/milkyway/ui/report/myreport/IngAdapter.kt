package com.milkyway.milkyway.ui.report.myreport

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.remote.response.IngReport


class IngAdapter (private val context : Context, var datas : List<IngReport>) : RecyclerView.Adapter<IngAdapter.IngViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngAdapter.IngViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_myreport_ing, parent, false)
        return IngViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: IngAdapter.IngViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    inner class IngViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cafename = itemView.findViewById<TextView>(R.id.item_myreport_ing_cafename)
        private val date = itemView.findViewById<TextView>(R.id.item_myreport_ing_date)


        fun onBind(ingReport: IngReport){
            val datetime = ingReport.created_at.substring(0,10)

            cafename.text = ingReport.cafeName
            date.text = datetime
        }
    }
}