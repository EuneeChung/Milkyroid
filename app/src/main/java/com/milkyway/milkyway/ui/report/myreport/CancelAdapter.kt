package com.milkyway.milkyway.ui.report.myreport


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.data.remote.response.CancelReport
import com.milkyway.milkyway.databinding.ItemMyreportCancelBinding

class CancelAdapter () : RecyclerView.Adapter<CancelAdapter.CancelViewHolder>() {
    var datas = mutableListOf<CancelReport>()
    lateinit var onClickListener: ()-> Unit
    var clickItemCafeId = 0
    var clickItemPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancelAdapter.CancelViewHolder {
        val binding = ItemMyreportCancelBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CancelViewHolder(binding)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: CancelViewHolder, position: Int) {
        holder.onBind(datas[position], position)
    }

    fun deleteItem(){
        datas.removeAt(clickItemPosition)
        notifyDataSetChanged()
    }


    inner class CancelViewHolder(val binding : ItemMyreportCancelBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(datas: CancelReport, position: Int){
            val datetime = datas.created_at.substring(0,10)

            binding.itemMyreportCancelCafename.text = datas.cafeName
            binding.itemMyreportCancelDate.text = datetime
            binding.itemClCancel.setOnClickListener{
                clickItemCafeId = datas.id
                clickItemPosition = position
                Log.d("클릭클릭", "${clickItemPosition}")
                onClickListener()
            }
        }
    }
}