package com.milkyway.milkyway.ui.home

import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import com.milkyway.milkyway.R

object HomeBinding {
    @BindingAdapter("setButtonChange")
    @JvmStatic
    fun setButtonChange(button : ImageButton, compass : Boolean) {
        if(compass) button.setBackgroundResource(R.drawable.btn_compass)
        else button.setBackgroundResource(R.drawable.btn_current_location)
    }
}