package com.milkyway.milkyway.ui.report.search

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R

class PlaceSearchAdapter : RecyclerView.Adapter<PlaceSearchAdapter.PlaceSearchViewHolder>() {

    var datas: MutableList<PlaceSearchData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_place_search, parent, false)
        return PlaceSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceSearchViewHolder, position: Int) {
        holder.bind(datas[position])
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    class PlaceSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val layout = itemView.findViewById<ConstraintLayout>(R.id.cl_item_place_search)
        private val placeName = itemView.findViewById<TextView>(R.id.tv_place_name)
        private val placeLocation = itemView.findViewById<TextView>(R.id.tv_place_location)
        private val alreadyCafe = itemView.findViewById<TextView>(R.id.tv_already_cafe)

        fun bind(placeSearchData: PlaceSearchData) {
            placeName.text = placeSearchData.cafeName
            placeLocation.text = placeSearchData.cafeAddress

            if (placeSearchData.isReported) { // 이미 등록된 view
                placeName.setTextColor(Color.parseColor("#9a9792"))
                placeLocation.setTextColor(Color.parseColor("#9a9792"))
                alreadyCafe.visibility=View.VISIBLE
                layout.isEnabled = false
            }

            if (!placeSearchData.isReported) { // 등록 안된 view
                placeName.setTextColor(Color.parseColor("#000000"))
                placeLocation.setTextColor(Color.parseColor("#9a9792"))
                alreadyCafe.visibility=View.INVISIBLE
                layout.isEnabled = true
            }
        }
    }

    fun clearData(){
        datas.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
}