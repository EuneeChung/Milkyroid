package com.milkyway.milkyway.ui.universe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.databinding.ItemMyuniverseBottomsheetBinding

class MyUniverseListAdapter : RecyclerView.Adapter<MyUniverseListViewHolder>(){
    var data = mutableListOf<String>("")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUniverseListViewHolder {
        val binding =ItemMyuniverseBottomsheetBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyUniverseListViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: MyUniverseListViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

class MyUniverseListViewHolder(val binding:ItemMyuniverseBottomsheetBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(data:String){
        binding.itemTvCafenameMyuniverse.text=data
    }

}