package com.milkyway.milkyway.ui.report.cafereport

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityCafeReportMenuBinding
import java.text.DecimalFormat

class CafeReportMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCafeReportMenuBinding
    var pointNumStr = "";
    var cp: String =""
    var cn: String =""
    private val categoryList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cafe_report_menu)
        binding.cafeReportMenuActivity = this
        binding.btnAddMenuOk.isEnabled=false
        binding.clCafeReportMenu.setOnClickListener {
            hideKeyboard()
        }

        binding.etCafePrice.addTextChangedListener(myTextWatcher)
        binding.etCafeMenuName.addTextChangedListener(myTextWatcher)
        binding.cbNoCaffeine.setOnCheckedChangeListener(myCheckWatcher)
        binding.cbDoyou.setOnCheckedChangeListener(myCheckWatcher)
        binding.cbLowMilk.setOnCheckedChangeListener(myCheckWatcher)
        binding.cbNoMilk.setOnCheckedChangeListener(myCheckWatcher)

        val preference: SharedPreferences = this.getSharedPreferences("temp", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor= preference.edit()

        val menu = intent.getStringExtra("menu")
        binding.etCafeMenuName.setText(menu)

        val price = intent.getStringExtra("price")
        binding.etCafePrice.setText(price)

        val category = intent.getIntegerArrayListExtra("category")
        if (category!=null)
        {
            when (1) {
                in category ->
                {binding.cbNoCaffeine.isChecked = true }
            }
            when (2) {
                in category -> {binding.cbDoyou.isChecked = true }
            }
            when (3) {
                in category -> {
                    binding.cbLowMilk.isChecked = true
                }
            }
            when (4) {
                in category -> {
                    binding.cbNoMilk.isChecked = true
                }
            }
        }

        deliveryCafeMenu(editor)



        binding.btnBackMenuAdd.setOnClickListener {
            onBackPressed()
        }

        binding.btnAddMenuOk.setOnClickListener {

            deliveryCafeMenu(editor)
            Log.d("posi", intent.getIntExtra("posi", -1).toString())
            setResult(1, intent.putIntegerArrayListExtra("categoryList", categoryList))
            finish()
        }
    }

    private val myTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            cp = binding.etCafePrice.text.toString().trim()
            cn = binding.etCafeMenuName.text.toString().trim()
            buttonActive()

            if (!TextUtils.isEmpty(cp.toString()) && cp.toString() != pointNumStr) {
                pointNumStr = makeCommaNumber(Integer.parseInt(cp.toString().replace(",", "")))
                binding.etCafePrice.setText(pointNumStr)
                binding.etCafePrice.setSelection(pointNumStr.length)
            }
        }
        override fun afterTextChanged(s: Editable) {}
    }

    private var myCheckWatcher = CompoundButton.OnCheckedChangeListener{view, isChecked ->
        if(isChecked){
            when(view)
            {
                binding.cbNoCaffeine->{categoryList.add(1)}
                binding.cbDoyou->{categoryList.add(2)}
                binding.cbLowMilk->{categoryList.add(3)}
                binding.cbNoMilk->{categoryList.add(4)}
            }
        }
        else
        {
            when(view)
            {
                binding.cbNoCaffeine->{categoryList.remove(1)}
                binding.cbDoyou->{categoryList.remove(2)}
                binding.cbLowMilk->{categoryList.remove(3)}
                binding.cbNoMilk->{categoryList.remove(4)}
            }
        }
        buttonActive()
    }

    fun buttonActive(){
        binding.btnAddMenuOk.isEnabled=(cp.isNotBlank() && cn.isNotBlank() && categoryList.isNotEmpty())
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun makeCommaNumber(input: Int): String {
            val formatter = DecimalFormat("###,###")
            return formatter.format(input)
    }

    private fun deliveryCafeMenu(editor: SharedPreferences.Editor) {
        editor.putString("menu", binding.etCafeMenuName.text.toString())
        editor.putString("price", binding.etCafePrice.text.toString())
        editor.putInt("posi", intent.getIntExtra("posi", -1))
        editor.clear()
        editor.apply()
        editor.commit()
    }

    companion object {
        const val REQUEST_CODE = 2003
        const val EDIT_CODE = 2000
    }
}