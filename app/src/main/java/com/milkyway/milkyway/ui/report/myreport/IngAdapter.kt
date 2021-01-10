package com.milkyway.milkyway.ui.report.myreport

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.ui.report.detail.CafeDetailActivity

class IngAdapter(private val context: Context) : RecyclerView.Adapter<IngAdapter.IngViewHolder>() {
    var data = mutableListOf<IngData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngAdapter.IngViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_myreport_ing, parent, false)
        return IngViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: IngAdapter.IngViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    inner class IngViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cafename = itemView.findViewById<TextView>(R.id.item_myreport_ing_cafename)
        private val date = itemView.findViewById<TextView>(R.id.item_myreport_ing_date)


        fun onBind(ingData: IngData){
            cafename.text = ingData.cafename
            date.text = ingData.price
        }
    }
}