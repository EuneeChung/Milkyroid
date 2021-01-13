package com.milkyway.milkyway.ui.report.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.util.DataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class PlaceSearchActivity : AppCompatActivity() {

    lateinit var placeSearchAdapter: PlaceSearchAdapter
    val placeDatas: MutableList<PlaceSearchData> = mutableListOf()
    private val dataStore = DataStore(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_search)
        val clPlaceSearch = findViewById<ConstraintLayout>(R.id.cl_place_search)
        val rv = findViewById<RecyclerView>(R.id.rv_place_search)
        val btn_back = findViewById<ImageView>(R.id.btn_back_search)
        val emptyView = findViewById<ConstraintLayout>(R.id.cl_empty_place_search)

        placeSearchAdapter = PlaceSearchAdapter()
        rv.adapter = placeSearchAdapter
        createData()

        //뒤로가기
        btn_back.setOnClickListener {
            onBackPressed()
        }

        //키보드 숨기기
        clPlaceSearch.setOnClickListener {
            hideKeyboard()
        }
        emptyView.setOnClickListener {
            hideKeyboard()
        }
        rv.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                hideKeyboard()
                return false
            }
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })

        //recycler 에서 아이템 클릭
        placeSearchAdapter.itemClick=object :PlaceSearchAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                val intent = Intent()
                intent.putExtra("placeName", placeSearchAdapter.datas[position].cafeName)
                intent.putExtra("placeAddress", placeSearchAdapter.datas[position].cafeAddress)
                intent.putExtra("placeLongitude", placeSearchAdapter.datas[position].longitude)
                intent.putExtra("placeLatitude", placeSearchAdapter.datas[position].latitude)
                setResult(2, intent)
                finish()
            }
        }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun createData() {
        val searchButton: ImageView = findViewById<ImageView>(R.id.btn_place_search)
        val deleteButton: ImageView = findViewById<ImageView>(R.id.btn_place_search_delete)
        val searchBox: EditText = findViewById<EditText>(R.id.et_place_search)
        val emptyView: ConstraintLayout = findViewById<ConstraintLayout>(R.id.cl_empty_place_search)
        val emptyImage: ImageView = findViewById<ImageView>(R.id.img_empty_place_search)
        val emptyText: TextView= findViewById<TextView>(R.id.tv_empty_place_search)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_place_search)

        val viewModel = ViewModelProvider(this).get(PlaceSearchActivityViewModel::class.java)
        viewModel.recyclerListData.observe(this, Observer<MutableList<PlaceSearchData>> {
            Log.d("d", it.toString())
            if (it.size > 0) {
                emptyView.visibility = View.INVISIBLE
                emptyImage.visibility=View.INVISIBLE
                emptyText.visibility=View.INVISIBLE
                recyclerView.visibility = View.VISIBLE
                placeSearchAdapter.datas = it
                placeSearchAdapter.notifyDataSetChanged()
            } else {
                emptyImage.visibility=View.VISIBLE
                emptyText.visibility=View.VISIBLE
                recyclerView.visibility = View.INVISIBLE
                this.hideKeyboard()
            }
        })

        //검색 -> x 로 변경
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

        //검색어 삭제
        deleteButton.setOnClickListener {
            emptyView.visibility=View.VISIBLE
            recyclerView.visibility=View.INVISIBLE
            searchBox.text.clear()
            placeSearchAdapter.clearData()
        }

        //키보드 검색버튼
        searchBox.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    lifecycleScope.launch {
                        DataStore(this@PlaceSearchActivity).getToken.collect {
                            viewModel.makeApiCall(searchBox.text.toString(), it!!)
                        }
                    }
                    return true
                }
                return false
            }
        })
    }

    companion object {
        const val REQUEST_CODE = 2002
    }
}