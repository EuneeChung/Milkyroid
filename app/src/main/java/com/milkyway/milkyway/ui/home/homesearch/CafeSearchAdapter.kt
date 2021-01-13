package com.milkyway.milkyway.ui.home.homesearch

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R

class CafeSearchAdapter : RecyclerView.Adapter<CafeSearchAdapter.CafeSearchViewHolder>(){

    var datas: MutableList<CafeSearchData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_place_search, parent, false)
        return CafeSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: CafeSearchViewHolder, position: Int) {
        holder.bind(datas[position])
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    class CafeSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val layout = itemView.findViewById<ConstraintLayout>(R.id.cl_item_place_search)
        private val cafeName = itemView.findViewById<TextView>(R.id.tv_place_name)
        private val cafeLocation = itemView.findViewById<TextView>(R.id.tv_place_location)

        fun bind(cafeSearchData: CafeSearchData) {
            cafeName.text = cafeSearchData.cafeName
            cafeLocation.text = cafeSearchData.cafeLocation

            if (cafeSearchData.alreadyIn) { // 이미 등록된 view만 보이게
                cafeName.setTextColor(Color.parseColor("#363636"))
                cafeLocation.setTextColor(Color.parseColor("#CDCDCD"))
                layout.isEnabled = true
            }

            if (!cafeSearchData.alreadyIn) { // 등록 안된 view
                cafeName.setTextColor(Color.parseColor("#E6E6E6"))
                cafeLocation.setTextColor(Color.parseColor("#E6E6E6"))
                layout.isEnabled = false
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