package com.milkyway.milkyway.ui.report.myreport

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.ui.report.detail.CafeDetailActivity

class CancelAdapter(private val context: Context) : RecyclerView.Adapter<CancelAdapter.CancelViewHolder>() {
    var data = mutableListOf<CancelData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancelAdapter.CancelViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_myreport_cancel, parent, false)
        return CancelViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CancelAdapter.CancelViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    inner class CancelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cafename = itemView.findViewById<TextView>(R.id.item_myreport_cancel_cafename)
        private val date = itemView.findViewById<TextView>(R.id.item_myreport_cancel_date)

        private val item_cl_cancel = itemView.findViewById<ConstraintLayout>(R.id.item_cl_cancel)


        fun onBind(cancelData: CancelData){
            cafename.text = cancelData.cafename
            date.text = cancelData.price

            // 아이템 클릭 이벤트
            item_cl_cancel.setOnClickListener(object :View.OnClickListener {
                override fun onClick(view: View?) {
//                    val context: Context = view!!.context
//                    val intent = Intent(view.context, CafeDetailActivity::class.java)
//
//                    context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))
                }
            })
        }
    }
}