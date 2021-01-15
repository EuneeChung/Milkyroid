package com.milkyway.milkyway.ui.report.myreport


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.data.remote.response.CancelReport
import com.milkyway.milkyway.databinding.ItemMyreportCancelBinding

// 주석처리한 것-> 바인딩 적용 전
class CancelAdapter () : RecyclerView.Adapter<CancelAdapter.CancelViewHolder>() {
    var datas = mutableListOf<CancelReport>()
    lateinit var onClickListener: ()-> Unit
    var clickItemCafeId = 0
    var clickItemPosition = 0

//class CancelAdapter (private val context : Context, var datas : List<CancelReport>) : RecyclerView.Adapter<CancelAdapter.CancelViewHolder>() {
//class CancelAdapter(private val context: Context) : RecyclerView.Adapter<CancelAdapter.CancelViewHolder>() {
//    var data = mutableListOf<CancelData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancelAdapter.CancelViewHolder {
        val binding = ItemMyreportCancelBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CancelViewHolder(binding)
//        return CancelAdapter.CancelViewHolder(binding)

//        val view = LayoutInflater.from(context).inflate(R.layout.item_myreport_cancel, parent, false)
//        return CancelAdapter.CancelViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size
//    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CancelViewHolder, position: Int) {
//    override fun onBindViewHolder(holder: CancelAdapter.CancelViewHolder, position: Int) {
        holder.onBind(datas[position], position)
//        holder.onBind(datas[position])
//        holder.onBind(data[position])
    }

    inner class CancelViewHolder(val binding : ItemMyreportCancelBinding) : RecyclerView.ViewHolder(binding.root){
//    inner class CancelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val cafename = itemView.findViewById<TextView>(R.id.item_myreport_cancel_cafename)
//        private val date = itemView.findViewById<TextView>(R.id.item_myreport_cancel_date)
//
//        private val item_cl_cancel = itemView.findViewById<ConstraintLayout>(R.id.item_cl_cancel)


        fun onBind(datas: CancelReport, position: Int){
//        fun onBind(cancelReport: CancelReport){
            val datetime = datas.created_at.substring(0,10)
//            val datetime = cancelReport.created_at.substring(0,10)

            binding.itemMyreportCancelCafename.text = datas.cafeName
            binding.itemMyreportCancelDate.text = datetime
            binding.itemClCancel.setOnClickListener{
                Log.e("어댑터카페아이디",datas.id.toString())
                clickItemCafeId = datas.id
                clickItemPosition = position
                onClickListener()
            }



            // 바인딩 아예 없는거
//            cafename.text = cancelReport.cafeName
//            date.text = datetime

//            // 아이템 클릭 팝업
//            item_cl_cancel.setOnClickListener {
//                ShowCancelDialog(context).show(null)
//            }


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