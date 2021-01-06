package com.milkyway.milkyway.ui.nickname

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.milkyway.milkyway.R
import java.util.regex.Pattern

object NicknameBinding {
    @BindingAdapter("setFilter")
    @JvmStatic
    fun setFilter(textView : TextView, nickname : String) {
        if (nickname.isEmpty()) textView.visibility = View.INVISIBLE
        else {
            if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname))
                textView.visibility = View.INVISIBLE
            else
                textView.visibility = View.VISIBLE
        }
    }

    @BindingAdapter("setEditTextBackground")
    @JvmStatic
    fun setEditTextBackground(editText : EditText, nickname : String) {
        if (nickname.isEmpty()) editText.setBackgroundResource(R.drawable.border_gray_line_round_8)
        else {
            if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname))
                editText.setBackgroundResource(R.drawable.border_navy_line_round_8)
            else
                editText.setBackgroundResource(R.drawable.border_red_line_round_8)
        }
    }

    @BindingAdapter("setLengthText")
    @JvmStatic
    fun setLengthText(textView : TextView, nickname : String) {
        textView.text = nickname.length.toString()
    }

    @BindingAdapter("setBtnActive")
    @JvmStatic
    fun setBtnActive(button : Button, nickname : String) {
        if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname))
            button.setBackgroundResource(R.drawable.border_navy_fill_round_40)
        else button.setBackgroundResource(R.drawable.border_gray_fill_round_40)
    }

    @BindingAdapter("setBtnCheck")
    @JvmStatic
    fun setBtnCheck(imageButton : ImageButton, nickname : String) {
        if (Pattern.matches("^[0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$", nickname)) imageButton.visibility = View.VISIBLE
        else imageButton.visibility = View.INVISIBLE
    }
}