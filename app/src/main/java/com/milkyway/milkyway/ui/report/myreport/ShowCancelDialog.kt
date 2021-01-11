package com.milkyway.milkyway.ui.report.myreport

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.DialogShowcancelMyreportBinding
import com.milkyway.milkyway.generated.callback.OnClickListener

class ShowCancelDialog(context: Context) {

    private lateinit var binding: DialogShowcancelMyreportBinding

    private val  builder: AlertDialog.Builder by lazy{
        binding= DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_showcancel_myreport,null,false)
        AlertDialog.Builder(context, R.style.AlertDialogRound8).setView(binding.root)
    }

    private var dialog: AlertDialog?=null

    // 확인 누르면 팝업창 닫기
    fun setPositiveButton(listener: OnClickListener?):ShowCancelDialog{
        binding.btnShowcancelConfirm.apply {
            setOnClickListener{ dismiss()}
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

