package com.milkyway.milkyway.ui.universe

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.data.remote.response.AroundUniverse

object UniverseBinding {
    @BindingAdapter("setListItem")
    @JvmStatic
    fun setListItem(recyclerView : RecyclerView, searchList : List<AroundUniverse>?) {
        if (recyclerView.adapter != null) with(recyclerView.adapter as UniverseAdapter) { searchList?.let{ setData(it) } }
    }
}