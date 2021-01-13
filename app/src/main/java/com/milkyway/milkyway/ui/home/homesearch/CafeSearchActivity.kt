package com.milkyway.milkyway.ui.home.homesearch

import android.content.Intent
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

class CafeSearchActivity : AppCompatActivity() {

    lateinit var cafeSearchAdapter: CafeSearchAdapter
    val cafeDatas: MutableList<CafeSearchData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_search)

        val rv = findViewById<RecyclerView>(R.id.rv_place_search)
        val btn_back = findViewById<ImageView>(R.id.btn_back_search)

        cafeSearchAdapter = CafeSearchAdapter()
        rv.adapter = cafeSearchAdapter
        createData()

        btn_back.setOnClickListener {
            onBackPressed()
        }

        cafeSearchAdapter.itemClick=object :CafeSearchAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                val intent = Intent()
                intent.putExtra("cafeName",cafeSearchAdapter.datas[position].cafeName)
                intent.putExtra("cafeLocation",cafeSearchAdapter.datas[position].cafeLocation)
                setResult(3,intent)
                finish()
            }
        }
    }

    private fun createData() {
        val searchButton: ImageView = findViewById<ImageView>(R.id.btn_place_search)
        val deleteButton: ImageView = findViewById<ImageView>(R.id.btn_place_search_delete)
        val searchBox: EditText = findViewById<EditText>(R.id.et_place_search)
        val emptyView: ConstraintLayout = findViewById<ConstraintLayout>(R.id.cl_empty_place_search)

        val viewModel = ViewModelProvider(this).get(CafeSearchViewModel::class.java)
        viewModel.recyclerListData.observe(this, Observer<MutableList<CafeSearchData>> {
            Log.d("d", it.toString())
            if (it != null) {
                cafeSearchAdapter.datas = it
                cafeSearchAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "에라", Toast.LENGTH_SHORT).show()
            }
        })

        searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchButton.visibility = View.VISIBLE
                deleteButton.visibility = View.INVISIBLE
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                searchButton.visibility = View.INVISIBLE
                deleteButton.visibility = View.VISIBLE
            }
        })

        deleteButton.setOnClickListener {
            searchBox.text.clear()
            viewModel.makeApiCall(searchBox.text.toString())
        }

        //키보드 검색버튼
        searchBox.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    emptyView.visibility = View.GONE
                    viewModel.makeApiCall(searchBox.text.toString())
                    return true
                }
                return false
            }
        })
    }

    companion object {
        const val REQUEST_CODE = 2005
    }
}