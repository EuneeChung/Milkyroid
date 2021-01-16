package com.milkyway.milkyway.ui.report.cafereport

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.CafeReportMenuData
import com.milkyway.milkyway.data.remote.RetrofitBuilder
import com.milkyway.milkyway.data.remote.request.CafeReportMenu
import com.milkyway.milkyway.data.remote.request.RequestReport
import com.milkyway.milkyway.data.remote.response.BaseResponse
import com.milkyway.milkyway.databinding.FragmentCafeReportBinding
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.ui.report.dialog.ShowReportOkDialog
import com.milkyway.milkyway.ui.search.reportsearch.PlaceSearchActivity
import com.milkyway.milkyway.util.DataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CafeReportFragment : Fragment() {
    private lateinit var binding: FragmentCafeReportBinding
    private lateinit var cafeReportMenuAdapter: CafeReportMenuAdapter
    private val cafeList = mutableListOf<CafeReportMenuData>()
    private val cafeMenu = mutableListOf<CafeReportMenu>()
    private var placeName : String = ""
    private var placeAddress : String = ""
    private var placeLongitude :Double= 1.1
    private var placeLatitude :Double= 1.1
    private var honeyList = mutableListOf<Int>()
    private var shareCafeMenu : String = ""
    private var sharedPrice : String = ""
    private var sharedCategory = arrayListOf<Int>()
    private lateinit var chipGroup: ChipGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cafe_report, container, false)
        binding.cafeReportFragment = this
        binding.lifecycleOwner = this

        binding.clCafeSearchAfter.visibility = View.INVISIBLE
        binding.clGoToCafeSearch.visibility = View.VISIBLE

        //칩 체크 확인
        chipGroup= binding.chipGroupHoneyTip
        for (index in 0 until chipGroup.childCount) {
            val chip: Chip = chipGroup.getChildAt(index) as Chip
            chip.setOnCheckedChangeListener { view, isChecked ->
                if (isChecked) {
                    chip.setTextAppearanceResource(R.style.HoneyChipSelectedTheme)
                    honeyList.add(index + 1)
                } else {
                    chip.setTextAppearanceResource(R.style.HoneyChipTheme)
                    honeyList.remove(index + 1)
                }
            }
        }

        //초기화 버튼
        binding.btnCafeReportClean.setOnClickListener {
            //칩 초기화
            chipGroup.clearCheck()
            honeyList.clear()

            //카페명 초기화
            binding.clCafeSearchAfter.visibility = View.INVISIBLE
            binding.clGoToCafeSearch.visibility = View.VISIBLE

            //카페메뉴 초기화
            cafeReportMenuAdapter.clearData()
            buttonActive()
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cafeReportMenuAdapter = CafeReportMenuAdapter(context as MainActivity)
        binding.rvCafeMenu.adapter = cafeReportMenuAdapter

        cafeReportMenuAdapter.itemClick = object : CafeReportMenuAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val popupMenu: PopupMenu = PopupMenu(
                    ContextThemeWrapper(
                        context,
                        R.style.PopupMenuStyle
                    ), view
                )
                popupMenu.inflate(R.menu.option_menu)
                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener() {
                    onMenuItemClick(context as MainActivity, it, position)
                })
                popupMenu.show()
            }
        }
    }

    //키보드 숨기기
    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun buttonActive(){
        binding.btnReportMenuOk.isEnabled = cafeReportMenuAdapter.itemCount>0 && !binding.tvCafeReportName.text.isNullOrBlank()
    }

    //메뉴 추가
    fun onAddMenuClick(view: View) {
        val addMenuIntent = Intent(context as MainActivity, CafeReportMenuActivity::class.java)
        startActivityForResult(addMenuIntent, CafeReportMenuActivity.REQUEST_CODE)
    }

    //카페 검색
    fun onCafeSearchClick(view: View) {
        val placeSearchIntent = Intent(context as MainActivity, PlaceSearchActivity::class.java)
        startActivityForResult(placeSearchIntent, PlaceSearchActivity.REQUEST_CODE)
    }

    //토글 클릭시
    private fun onMenuItemClick(
        context: Context,
        item: MenuItem,
        position: Int
    ): Boolean {
        when (item.itemId) {
            R.id.edit -> {
                val editMenuIntent =
                    Intent(context as MainActivity, CafeReportMenuActivity::class.java)
                editMenuIntent.putExtra("menu", cafeReportMenuAdapter.data[position].cafeMenuName.toString())
                editMenuIntent.putExtra("price", cafeReportMenuAdapter.data[position].cafeMenuPrice.toString())
                editMenuIntent.putIntegerArrayListExtra("category",sharedCategory)
                editMenuIntent.putExtra("posi", position) //리사이클러뷰 position
                startActivityForResult(editMenuIntent, CafeReportMenuActivity.EDIT_CODE)
            }
            R.id.delete -> {
                cafeReportMenuAdapter.removeItem(position)
                cafeReportMenuAdapter.notifyDataSetChanged()
                buttonActive()
            }
        }
        return false
    }

    //제보 완료
    fun onReportOkClick(view: View) {
        //토큰
        lifecycleScope.launch {
            DataStore(context as MainActivity).getToken.collect {
                if (it != null) {
                    requestReport(it)
                }
            }
        }

        binding.imgLoading.visibility=View.VISIBLE
        binding.imgLoading.playAnimation()

    }

    //서버통신
    private fun requestReport(token: String) {
        RetrofitBuilder.service.requestCafeReport(
            token = token,
            body = RequestReport(
                cafeName = placeName,
                cafeAddress = placeAddress,
                longitude = placeLongitude,
                latitude = placeLatitude,
                honeyTip = honeyList,
                menu = cafeMenu
            )
        ).enqueue(object : Callback<BaseResponse<Unit>> {
            override fun onResponse(
                call: Call<BaseResponse<Unit>>,
                response: Response<BaseResponse<Unit>>
            ) {
                Log.d("카페메뉴", cafeMenu.toString())
                if (response.isSuccessful) {
                    //dialog 띄우기
                        binding.imgLoading.visibility=View.GONE
                        binding.imgLoading.pauseAnimation()

                    lifecycleScope.launch {
                        DataStore(context as MainActivity).getNickname.collect {
                            if (it != null) {
                                val tabLayout = activity?.findViewById<TabLayout>(R.id.tab_report)
                                ShowReportOkDialog(context as MainActivity, it,tabLayout).show{
                                    //칩 초기화
                                    chipGroup.clearCheck()
                                    honeyList.clear()
                                    //카페명 초기화
                                    binding.clCafeSearchAfter.visibility = View.INVISIBLE
                                    binding.clGoToCafeSearch.visibility = View.VISIBLE
                                    //카페메뉴 초기화
                                    cafeReportMenuAdapter.clearData()
                                    buttonActive()
                                }
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<BaseResponse<Unit>>, t: Throwable) {
                Log.d("실패는 성공의 어머니", t.message.toString())
            }
        }
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("requestcode", requestCode.toString())
        Log.d("resultcode", resultCode.toString())
        if (resultCode == CAFE_RESULT) {
            if (requestCode == CafeReportMenuActivity.REQUEST_CODE) {
                var g: String = ""
                val preference = context?.getSharedPreferences("temp", Context.MODE_PRIVATE)
                shareCafeMenu = preference?.getString("menu", "").toString()
                sharedPrice = preference?.getString("price", "").toString()
                sharedCategory = data?.getIntegerArrayListExtra("categoryList")!!

                when (1) {
                    in sharedCategory -> g += "디카페인 "
                }
                when (2) {
                    in sharedCategory -> g += "두유 "
                }
                when (3) {
                    in sharedCategory -> g += "저지방우유 "
                }
                when (4) {
                    in sharedCategory -> g += "무지방우유 "
                }
                cafeList.apply {
                    add(
                        CafeReportMenuData(
                            cafeMenuName = shareCafeMenu.toString(),
                            cafeMenuPrice = sharedPrice.toString(),
                            cafeMenuCategory = g
                        )
                    )
                }

                cafeMenu.apply{
                    add(
                        CafeReportMenu(
                            menuName = shareCafeMenu.toString(),
                            price = sharedPrice.toString(),
                            category = sharedCategory
                        )
                    )
                }

                cafeReportMenuAdapter.data = cafeList
                cafeReportMenuAdapter.notifyDataSetChanged()
                buttonActive()
            }

            if (requestCode == CafeReportMenuActivity.EDIT_CODE) {
                var g: String = ""
                val preference = context?.getSharedPreferences("temp", Context.MODE_PRIVATE)
                shareCafeMenu = preference?.getString("menu", "").toString()
                sharedPrice = preference?.getString("price", "").toString()
                sharedCategory = data?.getIntegerArrayListExtra("categoryList")!!
                val posi = preference?.getInt("posi", -1)

                when (1) {
                    in sharedCategory -> g += "디카페인 "
                }
                when (2) {
                    in sharedCategory -> g += "두유 "
                }
                when (3) {
                    in sharedCategory -> g += "저지방우유 "
                }
                when (4) {
                    in sharedCategory -> g += "무지방우유 "
                }

                cafeList[posi!!].cafeMenuPrice = sharedPrice.toString()
                cafeList[posi].cafeMenuName = shareCafeMenu.toString()
                cafeList[posi].cafeMenuCategory = g

                cafeMenu[posi].price = sharedPrice.toString()
                cafeMenu[posi].menuName = shareCafeMenu.toString()
                cafeMenu[posi].category = sharedCategory

                cafeReportMenuAdapter.data = cafeList
                cafeReportMenuAdapter.notifyDataSetChanged()
                buttonActive()
            }
        }

        if (resultCode == SEARCH_RESULT) {
            binding.clCafeSearchAfter.visibility = View.VISIBLE
            binding.clGoToCafeSearch.visibility = View.INVISIBLE

            placeName = data?.getStringExtra("placeName").toString()
            placeAddress = data?.getStringExtra("placeAddress").toString()
            placeLongitude = data?.getDoubleExtra("placeLongitude", -1.1)!!
            placeLatitude = data?.getDoubleExtra("placeLatitude", -1.1)

            Log.d(
                "tgggfffw",
                placeName.toString() + placeAddress.toString() + placeLongitude.toString() + placeLatitude.toString()
            )

            binding.tvCafeReportName.text = placeName
            binding.textView13.text = placeAddress
            buttonActive()
        }
    }

    companion object {
        const val CAFE_RESULT = 1
        const val SEARCH_RESULT = 2
    }
}





