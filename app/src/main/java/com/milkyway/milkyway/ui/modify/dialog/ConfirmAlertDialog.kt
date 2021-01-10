package com.milkyway.milkyway.ui.modify.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.databinding.DataBindingUtil
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.DialogAlertWithBtnBinding
import com.milkyway.milkyway.generated.callback.OnClickListener

class ConfirmAlertDialog(context: Context) {

    private lateinit var binding: DialogAlertWithBtnBinding

    private val  builder: AlertDialog.Builder by lazy{
        binding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.dialog_alert_with_btn,null,false)
        AlertDialog.Builder(context,R.style.AlertDialogRound8).setView(binding.root)
    }

    private var dialog: AlertDialog?=null

    fun setPositiveButton(listener: OnClickListener?):ConfirmAlertDialog{
        binding.btnDialogConfirm.apply {
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

    fun show(listener: OnClickListener?){
        setWindow()
        dialog=builder.create()
        setPositiveButton(listener)
        dialog?.show()

    }
    fun dismiss(){
        dialog?.dismiss()
    }

}

