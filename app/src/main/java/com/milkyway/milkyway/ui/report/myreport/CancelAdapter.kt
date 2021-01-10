package com.milkyway.milkyway.ui.report.myreport

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.DialogShowcancelMyreportBinding
import com.milkyway.milkyway.generated.callback.OnClickListener
import com.milkyway.milkyway.ui.modify.dialog.ConfirmAlertDialog
import com.milkyway.milkyway.ui.modify.dialog.DeleteFragmentDialog
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


            // 아이템 클릭 팝업
            item_cl_cancel.setOnClickListener {
                ShowCancelDialog(context).show(null)
            }



//            // 바인딩 썼을때 (닫기는 안됨 - ShowCancelDialog 내용 18-23 bind()위에 써야함)
//            item_cl_cancel.setOnClickListener {
//                val dialog = builder.create()
//                val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_showcancel_myreport,null)
//                binding.btnShowcancelConfirm.setOnClickListener{
//                    dialog.dismiss()
//                }
//                dialog.setView(dialogView)
//                dialog.show()
//            }


//            // 바인딩 안썼을 때
//            item_cl_cancel.setOnClickListener {
//                val builder = AlertDialog.Builder(context)
//                val dialog = builder.create()
//                val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_showcancel_myreport,null)
//                val yes = dialogView.findViewById<MaterialButton>(R.id.btn_showcancel_confirm)
//                yes.setOnClickListener{
//                    dialog.dismiss()
//                }
//                dialog.setView(dialogView)
//                dialog.show()
//            }
        }
    }
}