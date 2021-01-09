package com.milkyway.milkyway.ui.report.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R

class MenuAdapter (private val context : Context) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    var data = mutableListOf<MenuData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_detailmenu, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val menutitle = itemView.findViewById<TextView>(R.id.item_detail_menutitle)
        private val menuprice = itemView.findViewById<TextView>(R.id.item_detail_menuprice)
        private val menutag = itemView.findViewById<TextView>(R.id.item_detail_menutag)


        fun onBind(menudata : MenuData){
            menutitle.text = menudata.title
            menuprice.text = menudata.price
            menutag.text = menudata.tag

        }
    }
}