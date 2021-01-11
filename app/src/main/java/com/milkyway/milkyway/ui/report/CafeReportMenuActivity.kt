package com.milkyway.milkyway.ui.report

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityCafeReportMenuBinding
import java.text.DecimalFormat

class CafeReportMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCafeReportMenuBinding
    var pointNumStr = "";
    private val categoryList = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cafe_report_menu)
        binding.cafeReportMenuActivity = this
        binding.lifecycleOwner = this

        val preference: SharedPreferences = this.getSharedPreferences("temp", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor= preference.edit()

        val menu = intent.getStringExtra("menu")
        binding.etCafeMenuName.setText(menu)

        val price = intent.getStringExtra("price")
        binding.etCafePrice.setText(price)

        binding.btnBackMenuAdd.setOnClickListener {
            onBackPressed()
        }

        binding.etCafePrice.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!TextUtils.isEmpty(p0.toString()) && !p0.toString().equals(pointNumStr)) {
                    pointNumStr = makeCommaNumber(Integer.parseInt(p0.toString().replace(",", "")))
                    binding.etCafePrice.setText(pointNumStr)
                    binding.etCafePrice.setSelection(pointNumStr.length)
                }
                /*
                if (!TextUtils.isEmpty(p0.toString())) {

                    }
                } else {
                    binding.btnAddMenuOk.isEnabled = false
                    binding.btnAddMenuOk.setBackgroundResource(R.drawable.border_gray_fill_round_40)
                }

                 */
            }
        })

        binding.btnAddMenuOk.isEnabled = true
        binding.btnAddMenuOk.setBackgroundResource(R.drawable.border_navy_fill_round_40)
        binding.btnAddMenuOk.setOnClickListener {
            if (binding.cbNoCaffeine.isChecked) {
                categoryList.add("1")
            }
            if (binding.cbDoyou.isChecked) {
                categoryList.add("2")
            }
            if (binding.cbLowMilk.isChecked) {
                categoryList.add("3")
            }
            if (binding.cbNoMilk.isChecked) {
                categoryList.add("4")
            }
            deliveryCafeMenu(editor)
            setResult(1,intent)
            finish()
        }
    }

    fun makeCommaNumber(input: Int): String {
            val formatter = DecimalFormat("###,###")
            return formatter.format(input)
    }

    private fun deliveryCafeMenu(editor: SharedPreferences.Editor) {
        editor.putString("menu",binding.etCafeMenuName.text.toString())
        editor.putString("price",binding.etCafePrice.text.toString())
        editor.putStringSet("category",categoryList)
        editor.putInt("posi",intent.getIntExtra("posi",-1))
        editor.apply()
        editor.commit()
    }

    companion object {
        const val REQUEST_CODE = 2003
        const val EDIT_CODE = 2000
    }
}