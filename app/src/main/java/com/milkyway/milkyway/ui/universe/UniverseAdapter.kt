package com.milkyway.milkyway.ui.universe

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.BR
import com.milkyway.milkyway.data.model.AroundUniverse
import com.milkyway.milkyway.databinding.ItemMyuniverseBottomsheetBinding
import com.milkyway.milkyway.ui.report.detail.CafeDetailActivity

class UniverseAdapter(private val context : Context) : RecyclerView.Adapter<UniverseAdapter.MyUniverseListViewHolder>(){
    var list = emptyList<AroundUniverse>()
    lateinit var onClickListener: ()-> Unit
    var clickItemCafeId=0
    var clickItemPosition=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyUniverseListViewHolder {
        val binding =ItemMyuniverseBottomsheetBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyUniverseListViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyUniverseListViewHolder, position: Int) {
        holder.bind(list[position],position)
    }

    internal fun setData(list : List<AroundUniverse>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyUniverseListViewHolder(val binding:ItemMyuniverseBottomsheetBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:AroundUniverse,position: Int){
            binding.setVariable(BR.cafe, data)
            binding.itemBtnDeleteMyuniverse.setOnClickListener{
                Log.e("data.cafeId",data.id.toString())
                clickItemCafeId=data.id
                clickItemPosition=position
                onClickListener()
            }
            binding.itemClUniverse.setOnClickListener{
                val intent = Intent(context, CafeDetailActivity::class.java)
                intent.putExtra("cafeId", list[position].id)
                context.startActivity(intent)
            }
        }
    }
}