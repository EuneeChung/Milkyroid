package com.milkyway.milkyway.ui.home.homesearch

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.CafeSearchData

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
        private val cafeEmptyView = itemView.findViewById<ConstraintLayout>(R.id.cl_empty_place_search)

        fun bind(cafeSearchData: CafeSearchData) {
            cafeName.text = cafeSearchData.cafeName
            cafeLocation.text = cafeSearchData.cafeAddress

            cafeName.setTextColor(Color.parseColor("#000000"))
            cafeLocation.setTextColor(Color.parseColor("#000000"))
            layout.isEnabled = true
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