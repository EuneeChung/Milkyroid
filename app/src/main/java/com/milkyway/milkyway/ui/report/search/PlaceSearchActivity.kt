package com.milkyway.milkyway.ui.report.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R

class PlaceSearchActivity : AppCompatActivity() {

    lateinit var placeSearchAdapter: PlaceSearchAdapter
    val placeDatas: MutableList<PlaceSearchData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_search)
        val rv = findViewById<RecyclerView>(R.id.rv_place_search)
        val btn_back = findViewById<ImageView>(R.id.btn_back_search)
        placeSearchAdapter = PlaceSearchAdapter()
        rv.adapter = placeSearchAdapter
        createData()

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun createData() {
        val viewModel = ViewModelProvider(this).get(PlaceSearchActivityViewModel::class.java)
        viewModel.recyclerListData.observe(this, Observer<MutableList<PlaceSearchData>> {
            Log.d("d", it.toString())
            if (it != null) {
                placeSearchAdapter.datas = it
                placeSearchAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "에라", Toast.LENGTH_SHORT).show()
            }
        })
        val searchbutton = findViewById<ImageView>(R.id.btn_place_search)
        val deletebutton = findViewById<ImageView>(R.id.btn_place_search_delete)
        val searchbox = findViewById<EditText>(R.id.et_place_search)
        val emptyview = findViewById<ConstraintLayout>(R.id.cl_empty_place_search)

        searchbox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchbutton.visibility = View.VISIBLE
                deletebutton.visibility = View.INVISIBLE
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                searchbutton.visibility = View.INVISIBLE
                deletebutton.visibility = View.VISIBLE
            }
        })

        deletebutton.setOnClickListener {
            searchbox.text.clear()
        }

        searchbox.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    emptyview.visibility = View.GONE
                    viewModel.makeApiCall(searchbox.text.toString())
                    return true
                }
                return false
            }
        })
    }
}