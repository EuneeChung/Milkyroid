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
import com.milkyway.milkyway.databinding.FragmentCafeReportBinding
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.ui.report.search.PlaceSearchActivity
import com.milkyway.milkyway.util.DataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CafeReportFragment : Fragment() {
    private lateinit var binding: FragmentCafeReportBinding
    private lateinit var cafeReportMenuAdapter: CafeReportMenuAdapter
    private val cafeList = mutableListOf<CafeReportMenuData>()

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
        val chipGroup: ChipGroup = binding.chipGroupHoneyTip
        val list = mutableListOf<Int>()
        for (index in 0 until chipGroup.childCount) {
            val chip: Chip = chipGroup.getChildAt(index) as Chip
            chip.setOnCheckedChangeListener { view, isChecked ->
                if (isChecked) {
                    chip.setTextAppearanceResource(R.style.HoneyChipSelectedTheme)
                    list.add(index + 1)
                } else {
                    chip.setTextAppearanceResource(R.style.HoneyChipTheme)
                    list.remove(index + 1)
                }
            }
        }

        //초기화 버튼
        binding.btnCafeReportClean.setOnClickListener {
            //칩 초기화
            chipGroup.clearCheck()
            list.clear()

            //카페명 초기화
            binding.clCafeSearchAfter.visibility = View.INVISIBLE
            binding.clGoToCafeSearch.visibility = View.VISIBLE

            //카페메뉴 초기화
            cafeReportMenuAdapter.clearData()
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
                editMenuIntent.putExtra(
                    "menu",
                    cafeReportMenuAdapter.data[position].cafeMenuName.toString()
                )
                editMenuIntent.putExtra(
                    "price",
                    cafeReportMenuAdapter.data[position].cafeMenuPrice.toString()
                )
                editMenuIntent.putExtra(
                    "category",
                    cafeReportMenuAdapter.data[position].cafeMenuPrice.toString()
                )
                editMenuIntent.putExtra("posi", position) //리사이클러뷰 position
                Log.d("asgwaeg", position.toString())
                startActivityForResult(editMenuIntent, CafeReportMenuActivity.EDIT_CODE)
            }
            R.id.delete -> {
                cafeReportMenuAdapter.removeItem(position)
            }
        }
        return false
    }

    //제보 완료
    fun onReportOkClick(view: View) {
        lifecycleScope.launch {
            DataStore(context as MainActivity).getNickname.collect {
                if (it != null) {
                    ShowReportOkDialog(context as MainActivity, it).show(null)
                }
            }
        }
        val tabLayout = activity?.findViewById<TabLayout>(R.id.tab_report)
        tabLayout?.selectTab(tabLayout.getTabAt(1))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("requestcode", requestCode.toString())
        Log.d("resultcode", resultCode.toString())
        if (resultCode == CAFE_RESULT) {
            if (requestCode == CafeReportMenuActivity.REQUEST_CODE) {
                var g: String = ""
                val preference = context?.getSharedPreferences("temp", Context.MODE_PRIVATE)
                val shareCafeMenu = preference?.getString("menu", "")
                val sharedPrice = preference?.getString("price", "")
                val sharedCategory = preference?.getStringSet("category", setOf())

                sharedCategory!!.toList()
                when ("1") {
                    in sharedCategory -> g += "디카페인 "
                }
                when ("2") {
                    in sharedCategory -> g += "두유 "
                }
                when ("3") {
                    in sharedCategory -> g += "저지방우유 "
                }
                when ("4") {
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
                cafeReportMenuAdapter.data = cafeList
                cafeReportMenuAdapter.notifyDataSetChanged()
            }

            if (requestCode == CafeReportMenuActivity.EDIT_CODE) {
                var g: String = ""
                val preference = context?.getSharedPreferences("temp", Context.MODE_PRIVATE)
                val shareCafeMenu = preference?.getString("menu", "")
                val sharedPrice = preference?.getString("price", "")
                val sharedCategory = preference?.getStringSet("category", setOf())
                val posi = preference?.getInt("posi", -1)
                Log.d("asgwaeg", posi.toString())
                sharedCategory!!.toList()
                when ("1") {
                    in sharedCategory -> g += "디카페인 "
                }
                when ("2") {
                    in sharedCategory -> g += "두유 "
                }
                when ("3") {
                    in sharedCategory -> g += "저지방우유 "
                }
                when ("4") {
                    in sharedCategory -> g += "무지방우유 "
                }

                cafeList[posi!!].cafeMenuPrice = sharedPrice.toString()
                cafeList[posi].cafeMenuName = shareCafeMenu.toString()
                cafeList[posi].cafeMenuCategory = g

                cafeReportMenuAdapter.data = cafeList
                cafeReportMenuAdapter.notifyDataSetChanged()
            }
        }

        if (resultCode == SEARCH_RESULT) {
            binding.clCafeSearchAfter.visibility = View.VISIBLE
            binding.clGoToCafeSearch.visibility = View.INVISIBLE

            val placeName = data?.getStringExtra("placeName")
            val placeAddress = data?.getStringExtra("placeAddress")
            val placeLongitude = data?.getDoubleExtra("placeLongitude", -1.1)
            val placeLatitude = data?.getDoubleExtra("placeLatitude", -1.1)

            Log.d(
                "tgggfffw",
                placeName.toString() + placeAddress.toString() + placeLongitude.toString() + placeLatitude.toString()
            )

            binding.tvCafeReportName.text = placeName
            binding.textView13.text = placeAddress
        }
    }


    companion object {
        const val CAFE_RESULT = 1
        const val SEARCH_RESULT = 2
    }
}





