package com.milkyway.milkyway.ui.report.detail

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.ui.report.detail.MenuData

class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val menutitle = itemView.findViewById<TextView>(R.id.item_detail_menutitle)
    private val menuprice = itemView.findViewById<TextView>(R.id.item_detail_menuprice)
    private val menutag = itemView.findViewById<TextView>(R.id.item_detail_menutag)


    fun onBind(menudata : MenuData){
        menutitle.text = menudata.title
        menuprice.text = menudata.price
        menutag.text = menudata.tag

    }
}