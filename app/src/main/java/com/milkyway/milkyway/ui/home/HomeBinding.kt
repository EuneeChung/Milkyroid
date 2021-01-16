package com.milkyway.milkyway.ui.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.milkyway.milkyway.R

object HomeBinding {
    @BindingAdapter("setButtonChange")
    @JvmStatic
    fun setButtonChange(button : ImageButton, compass : Boolean) {
        if(compass) button.setBackgroundResource(R.drawable.btn_compass)
        else button.setBackgroundResource(R.drawable.btn_current_location)
    }

    @BindingAdapter("loading")
    @JvmStatic
    fun loading(loading: LottieAnimationView, isLoading: Boolean) {
        if(isLoading) {
            loading.visibility = View.VISIBLE
            loading.playAnimation()
        } else {
            loading.visibility = View.GONE
            loading.pauseAnimation()
        }
    }

    @BindingAdapter("app:isSelected")
    @JvmStatic
    fun isSelected(textView : TextView, status: Boolean) {
        textView.isSelected = status
    }

    @BindingAdapter("app:marginBottom")
    @JvmStatic
    fun setMarginBottom(view: View, dimen: Float) {
        val layoutParams=view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.bottomMargin=dimen.toInt()
        view.layoutParams=layoutParams
    }
}