package com.milkyway.milkyway.ui.report.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.remote.response.CafeMenu

class MenuAdapter (private val context : Context, var datas : List<CafeMenu>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
//class MenuAdapter (private val context : Context) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
//    var data = mutableListOf<MenuData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_detailmenu, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size
//    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.onBind(datas[position])
//        holder.onBind(data[position])
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val menutitle = itemView.findViewById<TextView>(R.id.item_detail_menutitle)
        private val menuprice = itemView.findViewById<TextView>(R.id.item_detail_menuprice)
        private val menutag = itemView.findViewById<TextView>(R.id.item_detail_menutag)


        fun onBind(cafeMenu: CafeMenu){
            menutitle.text = cafeMenu.menuName
            menuprice.text = cafeMenu.price

            if(cafeMenu.category.any { it == 1 })
                menutag.text = "${menutag.text}디카페인  "
            if(cafeMenu.category.any { it == 2 })
                menutag.text = "${menutag.text}두유  "
            if(cafeMenu.category.any { it == 3 })
                menutag.text = "${menutag.text}저지방우유  "
            if(cafeMenu.category.any { it == 4 })
                menutag.text = "${menutag.text}무지방우유  "
        }
    }
}