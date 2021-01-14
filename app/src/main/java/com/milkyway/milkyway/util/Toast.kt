package com.milkyway.milkyway.util

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.milkyway.milkyway.R

object Toast {
    fun customToast(msg: String, context: Activity) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.item_custom_toast, context.findViewById(R.id.layout_custom_toast))

        Toast(context).apply {
            layout.findViewById<TextView>(R.id.tv_custom_toast).text = msg
            view = layout
            setGravity(Gravity.BOTTOM, 0, 200)
            duration = Toast.LENGTH_SHORT
            show()
        }
    }
}