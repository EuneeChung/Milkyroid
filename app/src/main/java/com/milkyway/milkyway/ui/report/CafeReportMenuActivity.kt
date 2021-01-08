package com.milkyway.milkyway.ui.report

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.milkyway.milkyway.R
import java.text.DecimalFormat

class CafeReportMenuActivity : AppCompatActivity() {
    var pointNumStr = "";
    var boolPrice: Boolean = false
    var boolMenu: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_report_menu)
        //buttonActivation(cafePrice,cafeMenu,addMenuButton)
        val cafePrice: EditText = findViewById<EditText>(R.id.et_cafe_price)
        val cafeMenu: EditText = findViewById<EditText>(R.id.et_cafe_menu_name)
        val addMenuButton: TextView = findViewById<TextView>(R.id.btn_add_menu_ok)
        //val cafePrice = findViewById<EditText>(R.id.et_cafe_price)
        cafePrice.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!TextUtils.isEmpty(p0.toString()) && !p0.toString().equals(pointNumStr)) {
                    pointNumStr = makeCommaNumber(Integer.parseInt(p0.toString().replace(",", "")))
                    cafePrice.setText(pointNumStr)
                    cafePrice.setSelection(pointNumStr.length)
                }
                if (!TextUtils.isEmpty(p0.toString())) {
                    boolPrice = true
                    //buttonActivation(true)
                    addMenuButton.isEnabled = true
                    addMenuButton.setBackgroundResource(R.drawable.border_navy_fill_round_40)
                    addMenuButton.setOnClickListener {
                        finish()
                    }
                }
                addMenuButton.isEnabled = false
                addMenuButton.setBackgroundResource(R.drawable.border_gray_fill_round_40)
            }
        })
    }

        fun makeCommaNumber(input: Int): String {
            val formatter = DecimalFormat("###,###")
            return formatter.format(input)
        }
        /*
               cafeMenu.addTextChangedListener(object :TextWatcher{
                   override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                   }
                   override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                       if(!TextUtils.isEmpty(s.toString()))
                       {
                           boolMenu=true

                           addMenuButton.isEnabled= true
                           addMenuButton.setBackgroundResource(R.drawable.border_navy_fill_round_40)
                           addMenuButton.setOnClickListener {
                               finish()
                           }

                    //buttonActivation(true)
                }
                /*
                addMenuButton.isEnabled = false
                addMenuButton.setBackgroundResource(R.drawable.border_gray_fill_round_40)
                 */
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
   }

/*
    private fun buttonActivation(boolMenu: Boolean, boolPrice: Boolean,addMenuButton:TextView) {
        if(this.boolMenu && this.boolPrice)
        {
            addMenuButton.isEnabled= true
            addMenuButton.setBackgroundResource(R.drawable.border_navy_fill_round_40)
            addMenuButton.setOnClickListener {
                finish()
            }
        }
        addMenuButton.isEnabled = false
        addMenuButton.setBackgroundResource(R.drawable.border_gray_fill_round_40)
        this.boolMenu=false
        this.boolPrice=false

        transMenu()
    }

    private fun transMenu() {
        cafeMenu.text.toString()
        cafePrice.text.toString()
    }


 */
         */

}