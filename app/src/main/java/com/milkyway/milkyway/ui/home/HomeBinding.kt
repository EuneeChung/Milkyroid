package com.milkyway.milkyway.ui.home

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
}