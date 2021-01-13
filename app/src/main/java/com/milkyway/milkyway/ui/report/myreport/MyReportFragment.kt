package com.milkyway.milkyway.ui.report.myreport

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.provider.Settings
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.CafeMenu
import com.milkyway.milkyway.data.model.CancelReport
import com.milkyway.milkyway.data.model.DoneReport
import com.milkyway.milkyway.data.model.IngReport
import com.milkyway.milkyway.databinding.ActivityCafeDetailBinding
import com.milkyway.milkyway.databinding.FragmentMyReportBinding
import com.milkyway.milkyway.ui.report.detail.MenuAdapter
import com.milkyway.milkyway.util.DataStore
import com.milkyway.milkyway.util.UUID
import com.milkyway.milkyway.util.UUID.uuid
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MyReportFragment : Fragment() {
    // 뷰모델 선언 (ktx 이용)
    private val myReportViewModel: MyReportViewModel by activityViewModels()
    private lateinit var binding: FragmentMyReportBinding

    // 리사이클러뷰 적용
    lateinit var cancelAdapter: CancelAdapter
    lateinit var ingAdapter: IngAdapter
    lateinit var doneAdapter: DoneAdapter

    val canceldatas = mutableListOf<CancelReport>()
    val ingdatas = mutableListOf<IngReport>()
    val donedatas = mutableListOf<DoneReport>()


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

        // 어댑터에 context 객체를 파라미터로 전달
//        cancelAdapter = CancelAdapter(view.context)   // 더미
        cancelAdapter = CancelAdapter(view.context, canceldatas)
        ingAdapter = IngAdapter(view.context, ingdatas)
        doneAdapter= DoneAdapter(view.context, donedatas)

        binding.rvMyreportCancel.adapter = cancelAdapter
        binding.rvMyreportIng.adapter = ingAdapter
        binding.rvMyreportDone.adapter = doneAdapter


//        binding.rvMyreportCancel.layoutManager = LinearLayoutManager(binding.rvMyreportCancel.context)    // 이것도 가능
        binding.rvMyreportCancel.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMyreportIng.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMyreportDone.layoutManager = LinearLayoutManager(view.context)

//        myReportViewModel.RVCancel()
//        myReportViewModel.recyclerListData.observe(viewLifecycleOwner, Observer {
//            cancelAdapter.data = it   // 더미데이터 들어감
//            cancelAdapter.notifyDataSetChanged()
//        })

        setReportData(binding)
    }


    private fun setReportData(binding: FragmentMyReportBinding) {
        // 어댑터에 context 객체를 파라미터로 전달 (view.context 때문에 위로 올렸음)
        // 토큰값 얻어오며 서버요청
        lifecycleScope.launch {
            DataStore(requireContext()).getToken.collect {
                myReportViewModel.requestReportData(it!!) // 아래는 서버테스트용
//                myReportViewModel.requestReportData("")
            }
        }
        myReportViewModel.recyclerListData.observe(viewLifecycleOwner, Observer {
//            if(it.cancel.isEmpty()&&it.cancel.isEmpty()&&it.cancel.isEmpty()){
//                binding.clMyreport.visibility = View.GONE
//                binding.clMyreportEmpty.visibility = View.VISIBLE
//                Log.d("로그", "전체 없음")
//            }
//            else{
                cancelAdapter.datas = it.cancel   // 서버데이터 들어감
                if(it.cancel.isEmpty()){
                    binding.tvMyreportCancel.visibility = View.GONE
                    binding.rvMyreportCancel.visibility = View.GONE
                    Log.d("로그취소", "취소 없음")
                }
                cancelAdapter.notifyDataSetChanged()

                ingAdapter.datas = it.ing
                if(it.ing.isEmpty()){
                    binding.rvMyreportIng.visibility = View.GONE
                    binding.tvMyreportIngEmpty.visibility = View.VISIBLE
                    Log.d("로그", "진행중 없음")
                }
                ingAdapter.notifyDataSetChanged()

                doneAdapter.datas = it.done
                if (it.done.isEmpty()) {
                    binding.rvMyreportDone.visibility = View.GONE
                    binding.tvMyreportDoneEmpty.visibility = View.VISIBLE
                    Log.d("로그", "완료 없음")
                }
                doneAdapter.notifyDataSetChanged()
//            }
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