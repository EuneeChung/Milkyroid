package com.milkyway.milkyway.ui.home

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:isSelected")
fun View.isSelected(status: Boolean) {
    isSelected = status
}