package com.milkyway.milkyway.ui.report.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R

class MenuAdapter (private val context : Context) : RecyclerView.Adapter<MenuViewHolder>() {
    var data = mutableListOf<MenuData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_detailmenu, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}