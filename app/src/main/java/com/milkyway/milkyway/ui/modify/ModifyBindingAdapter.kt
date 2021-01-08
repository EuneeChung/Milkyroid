package com.milkyway.milkyway.ui.modify

import android.widget.Button
import androidx.databinding.BindingAdapter

@BindingAdapter("app:isBtnActive")
fun isBtnActive(button:Button,isActive: Boolean){
    button.isEnabled = isActive
}

