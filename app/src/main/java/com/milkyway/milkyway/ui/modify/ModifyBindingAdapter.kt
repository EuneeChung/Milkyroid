package com.milkyway.milkyway.ui.modify

import android.util.Log
import android.widget.Button
import androidx.databinding.BindingAdapter
import com.milkyway.milkyway.R

@BindingAdapter("app:isBtnActive")
fun isBtnActive(button:Button,isActive: Boolean){
    button.isEnabled = isActive
}

