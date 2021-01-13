package com.milkyway.milkyway.ui.universe

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.data.model.MyUniverse
import com.milkyway.milkyway.databinding.ItemMyuniverseBottomsheetBinding

class MyUniverseListAdapter() : RecyclerView.Adapter<MyUniverseListAdapter.MyUniverseListViewHolder>(){
    var data = mutableListOf<MyUniverse>()
    lateinit var onClickListener: ()-> Unit
    var clickItemCafeId=0
    var clickItemPosition=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUniverseListViewHolder {
        val binding =ItemMyuniverseBottomsheetBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyUniverseListViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyUniverseListViewHolder, position: Int) {
        holder.bind(data[position],position)

    }

    inner class MyUniverseListViewHolder(val binding:ItemMyuniverseBottomsheetBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:MyUniverse,position: Int){
            binding.itemTvCafenameMyuniverse.text=data.cafeName
            binding.itemTvAddressMyuniverse.text=data.cafeName
            binding.itemBtnDeleteMyuniverse.setOnClickListener{
                Log.e("data.cafeId",data.cafeId.toString())
                clickItemCafeId=data.cafeId
                clickItemPosition=position
                onClickListener()
            }
        }
    }

}

