package com.milkyway.milkyway.ui.report.myreport

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.DoneReport
import com.milkyway.milkyway.ui.detail.CafeDetailActivity

class DoneAdapter (private val context : Context, var datas : List<DoneReport>) : RecyclerView.Adapter<DoneAdapter.DoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneAdapter.DoneViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_myreport_done, parent, false)
        return DoneViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: DoneAdapter.DoneViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    inner class DoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val date = itemView.findViewById<TextView>(R.id.item_myreport_done_date)
        private val cafename = itemView.findViewById<TextView>(R.id.item_myreport_done_cafename)
        private val location = itemView.findViewById<TextView>(R.id.item_myreport_done_location)
        private val item_myreport_done_tag1 = itemView.findViewById<TextView>(R.id.item_myreport_done_tag1)
        private val item_myreport_done_tag2 = itemView.findViewById<TextView>(R.id.item_myreport_done_tag2)
        private val item_myreport_done_tag3 = itemView.findViewById<TextView>(R.id.item_myreport_done_tag3)
        private val item_myreport_done_tag4 = itemView.findViewById<TextView>(R.id.item_myreport_done_tag4)

        private val item_cl_done = itemView.findViewById<ConstraintLayout>(R.id.item_cl_done)


        fun onBind(doneReport: DoneReport){
            val datetime = doneReport.created_at.substring(0,10)

            date.text = datetime
            cafename.text = doneReport.cafeName
            location.text = doneReport.cafeAddress

            if(doneReport.category.any { it == 1 })
                item_myreport_done_tag1.isVisible = true
            if(doneReport.category.any { it == 2 })
                item_myreport_done_tag2.isVisible = true
            if(doneReport.category.any { it == 3 })
                item_myreport_done_tag4.isVisible = true
            if(doneReport.category.any { it == 4 })
                item_myreport_done_tag3.isVisible = true

            // 아이템 클릭 이벤트
            item_cl_done.setOnClickListener(object :View.OnClickListener {
                override fun onClick(view: View?) {
                    val context: Context = view!!.context
                    val intent = Intent(view.context, CafeDetailActivity::class.java)

                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                }
            })
        }
    }
}