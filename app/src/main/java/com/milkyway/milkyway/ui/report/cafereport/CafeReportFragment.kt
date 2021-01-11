package com.milkyway.milkyway.ui.report.cafereport

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.FragmentCafeReportBinding
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.ui.report.search.PlaceSearchActivity


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
        cafeReportMenuAdapter = CafeReportMenuAdapter(context as MainActivity)
        binding.clCafeSearchAfter.visibility = View.INVISIBLE
        binding.clGoToCafeSearch.visibility = View.VISIBLE

        //칩 체크 확인
        val chipGroup : ChipGroup = binding.chipGroupHoneyTip
        val list = mutableListOf<Int>()
        for (index in 0 until chipGroup.childCount) {
            val chip: Chip = chipGroup.getChildAt(index) as Chip
            chip.setOnCheckedChangeListener { view, isChecked ->
                if (isChecked) {
                    list.add(index+1)
                } else {
                    list.remove(index+1)
                }
                if (list.isNotEmpty()) {
                    Toast.makeText(context, "Selected $list", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    fun onAddMenuClick(view: View) {
        val addMenuIntent = Intent(context as MainActivity, CafeReportMenuActivity::class.java)
        startActivityForResult(addMenuIntent, CafeReportMenuActivity.REQUEST_CODE)
    }

    fun onCafeSearchClick(view: View) {
        val placeSearchIntent = Intent(context as MainActivity, PlaceSearchActivity::class.java)
        startActivityForResult(placeSearchIntent, PlaceSearchActivity.REQUEST_CODE)
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == CAFE_RESULT && requestCode == CafeReportMenuActivity.REQUEST_CODE) {

            //cafeReportMenuAdapter.data.clear()
            var g: String = ""
            val preference = context?.getSharedPreferences("temp", Context.MODE_PRIVATE)
            val shareCafeMenu = preference?.getString("menu", "")
            val sharedPrice = preference?.getString("price", "")
            val sharedCategory = preference?.getStringSet("category", setOf())
            //val separatedSerialNumber = (sharedCategory.toString().joinToString (","){it}

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
            binding.rvCafeMenu.adapter = cafeReportMenuAdapter
            cafeReportMenuAdapter.notifyDataSetChanged()
        }

        if (resultCode == SEARCH_RESULT) {

            binding.clCafeSearchAfter.visibility = View.VISIBLE
            binding.clGoToCafeSearch.visibility = View.INVISIBLE

            val placeName = data?.getStringExtra("placeName")
            val placeLocation = data?.getStringExtra("placeLocation")

            binding.tvCafeReportName.text = placeName
            binding.textView13.text=placeLocation
        }

/*
        if (requestCode == CafeReportMenuActivity.EDIT_CODE) {
            Log.d("수정수정수정", "cafe 리폴트 프래그먼트에서 호출")
            Toast.makeText(context, "수정화면으로", Toast.LENGTH_SHORT).show()
            cafeReportMenuAdapter = CafeReportMenuAdapter(context as MainActivity)
            cafeReportMenuAdapter.data.clear()

            var g: String = ""
            val preference = context?.getSharedPreferences("temp", Context.MODE_PRIVATE)
            val shareCafeMenu = preference?.getString("menu", "")
            val sharedPrice = preference?.getString("price", "")
            val sharedCategory = preference?.getStringSet("category", setOf())

            val placeName = data?.getStringExtra("menu")
            val posi = data?.getIntExtra("posi", -1)
            posi?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
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
            cafeList[posi!!].cafeMenuPrice = placeName.toString()
            cafeList[posi].cafeMenuName = shareCafeMenu.toString()
            cafeList[posi].cafeMenuCategory = g

            cafeReportMenuAdapter.data = cafeList

            binding.rvCafeMenu.adapter = cafeReportMenuAdapter
            cafeReportMenuAdapter.notifyDataSetChanged()
        }
 */
    }

    companion object {
        const val CAFE_RESULT = 1
        const val SEARCH_RESULT = 2
    }
}





