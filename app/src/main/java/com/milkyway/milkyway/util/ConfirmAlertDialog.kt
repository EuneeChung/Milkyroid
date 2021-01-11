package com.milkyway.milkyway.ui.universe

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.databinding.DataBindingUtil
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.DialogAlertWithTitleBinding
import com.milkyway.milkyway.databinding.DialogAlertWithoutTitleBinding
import com.milkyway.milkyway.databinding.DialogDeleteUniverseBinding

class ConfirmAlertDialog(context: Context, val type:Int) {

    private lateinit var bindingWithTitle: DialogAlertWithTitleBinding
    private lateinit var bindingWithoutTitle: DialogAlertWithoutTitleBinding
    private lateinit var bindingDeleteUniverse: DialogDeleteUniverseBinding
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

    fun create() {
        dialog = builder.create()
    }

    fun show(listener: View.OnClickListener?):ConfirmAlertDialog {
        setWindow()
        dialog = builder.create()
//        dialog?.setCancelable(false)
//        dialog?.setCanceledOnTouchOutside(false)
        setPositiveButton(listener)
        dialog?.show()
        return this
    }

    fun dismiss() {
        dialog?.dismiss()
    }


    fun setPositiveButton(listener: View.OnClickListener?) {
        when(type){
            MODIFY_CONFIRM -> {
                bindingWithTitle.btnDialogConfirm.apply {
                    setOnClickListener { dismiss() }
                }
            }
            UNIVERSE_CONFIRM -> {
                bindingWithoutTitle.btnDialogConfirm.apply {
                    setOnClickListener { listener }
                }
            }
            UNIVERSE_DELETE -> {
                bindingDeleteUniverse.btnNegative.apply {
                    setOnClickListener { dismiss() }
                }
                bindingDeleteUniverse.btnPositive.apply {
                    setOnClickListener { listener }
                }
            }
        }
    }

    companion object {
        private const val MODIFY_CONFIRM = 1
        private const val UNIVERSE_CONFIRM = 2
        private const val UNIVERSE_DELETE = 3
    }

}

