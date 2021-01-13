package com.milkyway.milkyway.ui.report.cafereport
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.DailogReportOkBinding
import com.milkyway.milkyway.generated.callback.OnClickListener


class ShowReportOkDialog(context: Context, s: String) {

    private lateinit var binding: DailogReportOkBinding
    private lateinit var activity : Activity

    private val builder: AlertDialog.Builder by lazy{
        binding= DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dailog_report_ok, null, false
        )

        binding.tvShowReportNicknname.text = s
        AlertDialog.Builder(context, R.style.AlertDialogRound8).setView(binding.root)
    }

    private var dialog: AlertDialog?=null

    // 확인 누르면 팝업창 닫기
    fun setPositiveButton(listener: OnClickListener?): ShowReportOkDialog {
        binding.btnShowReportConfirm.apply {
            setOnClickListener{
               dismiss()
            }
        }
        return this
    }

    private fun setWindow(){
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
        }
    }

    //    fun show(listener: OnClickListener?){
    fun show(listener: OnClickListener?){
        setWindow()
        dialog = builder.create()
        setPositiveButton(listener)
        dialog?.show()
    }

    fun dismiss(){
        dialog?.dismiss()
    }
}
