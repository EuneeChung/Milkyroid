package com.milkyway.milkyway.ui.report.myreport

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.ui.report.detail.CafeDetailActivity

class DoneAdapter(private val context: Context) : RecyclerView.Adapter<DoneAdapter.DoneViewHolder>() {
    var data = mutableListOf<DoneData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneAdapter.DoneViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_myreport_done, parent, false)
        return DoneViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DoneAdapter.DoneViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    inner class DoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val date = itemView.findViewById<TextView>(R.id.item_myreport_done_date)
        private val cafename = itemView.findViewById<TextView>(R.id.item_myreport_done_cafename)
        private val location = itemView.findViewById<TextView>(R.id.item_myreport_done_location)

        private val item_cl_done = itemView.findViewById<ConstraintLayout>(R.id.item_cl_done)


        fun onBind(doneData: DoneData){
            date.text = doneData.date
            cafename.text = doneData.cafename
            location.text = doneData.location

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