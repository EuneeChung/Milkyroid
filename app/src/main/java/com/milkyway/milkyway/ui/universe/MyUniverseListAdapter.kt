package com.milkyway.milkyway.ui.universe

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.data.model.AroundUniverse
import com.milkyway.milkyway.databinding.ItemMyuniverseBottomsheetBinding

class MyUniverseListAdapter() : RecyclerView.Adapter<MyUniverseListAdapter.MyUniverseListViewHolder>(){
    var data = mutableListOf<AroundUniverse>()
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
        fun bind(data:AroundUniverse,position: Int){
            binding.itemTvCafenameMyuniverse.text=data.cafeName
            binding.itemTvAddressMyuniverse.text=data.cafeAddress
            binding.itemBtnDeleteMyuniverse.setOnClickListener{
                Log.e("data.cafeId",data.id.toString())
                clickItemCafeId=data.id
                clickItemPosition=position
                onClickListener()
            }
        }
    }

}

