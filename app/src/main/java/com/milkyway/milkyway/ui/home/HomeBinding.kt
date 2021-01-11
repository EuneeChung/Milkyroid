package com.milkyway.milkyway.ui.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.milkyway.milkyway.R

object HomeBinding {
    @BindingAdapter("setButtonChange")
    @JvmStatic
    fun setButtonChange(button : ImageButton, compass : Boolean) {
        if(compass) button.setBackgroundResource(R.drawable.btn_compass)
        else button.setBackgroundResource(R.drawable.btn_current_location)
    }

    @BindingAdapter("app:isSelected")
    @JvmStatic
    fun isSelected(textView : TextView, status: Boolean) {
        textView.isSelected = status
    }

    @BindingAdapter("app:marginBottom")
    @JvmStatic
    fun setMarginBottom(view: View, status: Boolean) {
        val layoutParams=view.layoutParams as ViewGroup.MarginLayoutParams
        if(status){layoutParams.bottomMargin=20}
        else{layoutParams.bottomMargin=260}
        view.layoutParams=layoutParams
    }
}