package com.milkyway.milkyway.util

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.DialogAlertWithTitleBinding
import com.milkyway.milkyway.databinding.DialogAlertWithoutTitleBinding
import com.milkyway.milkyway.databinding.DialogDeleteUniverseBinding
import com.milkyway.milkyway.databinding.DialogShowcancelMyreportBinding

class ConfirmAlertDialog(context: Context, val type:Int) {

    private lateinit var bindingWithTitle: DialogAlertWithTitleBinding
    private lateinit var bindingWithoutTitle: DialogAlertWithoutTitleBinding
    private lateinit var bindingDeleteUniverse: DialogDeleteUniverseBinding
    private lateinit var bindingDeleteCancel: DialogShowcancelMyreportBinding

    private val builder: AlertDialog.Builder by lazy {
        when (type) {
            MODIFY_CONFIRM -> {
                bindingWithTitle = DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.dialog_alert_with_title, null, false)
                AlertDialog.Builder(context, R.style.AlertDialogRound8).setView(bindingWithTitle.root)
            }
            UNIVERSE_CONFIRM -> {
                bindingWithoutTitle = DataBindingUtil.inflate(
                    LayoutInflater.from(context), R.layout.dialog_alert_without_title, null, false)
                AlertDialog.Builder(context, R.style.AlertDialogRound8).setView(bindingWithoutTitle.root)
            }
            UNIVERSE_DELETE -> {
                bindingDeleteUniverse = DataBindingUtil.inflate(
                    LayoutInflater.from(context), R.layout.dialog_delete_universe, null, false)
                AlertDialog.Builder(context, R.style.AlertDialogRound8).setView(bindingDeleteUniverse.root)
            }
            CANCEL_DELETE -> {
                bindingDeleteCancel = DataBindingUtil.inflate(
                    LayoutInflater.from(context), R.layout.dialog_showcancel_myreport, null, false)
                AlertDialog.Builder(context, R.style.AlertDialogRound8).setView(bindingDeleteCancel.root)
            }
            else -> AlertDialog.Builder(context, R.style.AlertDialogRound8).setView(bindingWithoutTitle.root)
        }

    }

    private var dialog: AlertDialog? = null


    private fun setWindow() {
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
        }
    }

    fun create():ConfirmAlertDialog {
        setWindow()
        dialog = builder.create()
        return this
    }

    fun show(listener: ()->Unit):ConfirmAlertDialog{

        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        setPositiveButton(listener)
        dialog?.show()
        return this
    }

    fun dismiss() {
        dialog?.dismiss()
    }


    fun setPositiveButton(listener:()->Unit) {
        when(type){
            MODIFY_CONFIRM -> {
                bindingWithTitle.btnDialogConfirm.apply {
                    setOnClickListener {
                        listener()
                        dismiss()
                    }
                }
            }
            UNIVERSE_CONFIRM -> {
                bindingWithoutTitle.btnDialogConfirm.apply {
                    setOnClickListener {
                        listener()
                        dismiss()
                    }
                }
            }
            UNIVERSE_DELETE -> {
                bindingDeleteUniverse.btnNegative.apply {
                    setOnClickListener { dismiss() }
                }
                bindingDeleteUniverse.btnPositive.apply {
                    setOnClickListener {
                        listener()
                        dismiss()
                    }

                }
            }
            CANCEL_DELETE -> {
                bindingDeleteCancel.btnShowcancelConfirm.apply {
                    setOnClickListener {
                        listener()
//                        dismiss()
                    }
                }
            }
        }
    }

    companion object {
        private const val MODIFY_CONFIRM = 1
        private const val UNIVERSE_CONFIRM = 2
        private const val UNIVERSE_DELETE = 3
        private const val CANCEL_DELETE = 4
    }

}

