package com.milkyway.milkyway.ui.report.myreport

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.FragmentMyReportBinding


class MyReportFragment : Fragment() {
    // 뷰모델 선언 (ktx 이용)
    private val myReportViewModel: MyReportViewModel by activityViewModels()
    private lateinit var binding: FragmentMyReportBinding

    // 리사이클러뷰 적용
    lateinit var cancelAdapter: CancelAdapter
    lateinit var ingAdapter: IngAdapter
    lateinit var doneAdapter: DoneAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyReportBinding.inflate(inflater, container, false)
        // onViewCreated에서도 binding에 접근할 수 있도록 위처럼 lateinit으로 처리
        // val binding : FragmentMyReportBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_report, container, false)

        binding.myreportviewModel = myReportViewModel
        binding.lifecycleOwner = this

//        setTextNormal(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var datas: MutableList<CancelData> = mutableListOf<CancelData>()

        // 어댑터에 context 객체를 파라미터로 전달 (더미)
        cancelAdapter = CancelAdapter(view.context)
        ingAdapter = IngAdapter(view.context)
        doneAdapter = DoneAdapter(view.context)

        binding.rvMyreportCancel.adapter = cancelAdapter
        binding.rvMyreportIng.adapter = ingAdapter
        binding.rvMyreportDone.adapter = doneAdapter

//        binding.rvMyreportCancel.layoutManager = LinearLayoutManager(binding.rvMyreportCancel.context)    // 이것도 가능
        binding.rvMyreportCancel.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMyreportIng.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMyreportDone.layoutManager = LinearLayoutManager(view.context)

        myReportViewModel.RVCancel()
        myReportViewModel.RVIng()
        myReportViewModel.RVDone()


        myReportViewModel.recyclerListData.observe(viewLifecycleOwner, Observer {
            cancelAdapter.data = it   // 더미데이터 들어감
            cancelAdapter.notifyDataSetChanged()
        })
        myReportViewModel.recyclerListData2.observe(viewLifecycleOwner, Observer {
            ingAdapter.data = it   // 더미데이터 들어감
            ingAdapter.notifyDataSetChanged()
        })
        myReportViewModel.recyclerListData3.observe(viewLifecycleOwner, Observer {
            doneAdapter.data = it   // 더미데이터 들어감
            doneAdapter.notifyDataSetChanged()
        })
    }

    // 특정 글자 얇게
    private fun setTextNormal(binding: FragmentMyReportBinding) {
        val content = binding.tvMyreportTitle.text.toString()
        val spannableString = SpannableString(content)

        val word = "덕분에\n밀키웨이가 빛나고 있어요!"
        val start = content.indexOf(word)
        val end = start + word.length

        spannableString.setSpan(
            StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //시도중
        spannableString.setSpan(
            R.font.roboto_regular, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.tvMyreportTitle.text = spannableString
    }
}