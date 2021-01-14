package com.milkyway.milkyway.ui.report.myreport

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.CancelReport
import com.milkyway.milkyway.data.model.DoneReport
import com.milkyway.milkyway.data.model.IngReport
import com.milkyway.milkyway.databinding.FragmentMyReportBinding
import com.milkyway.milkyway.ui.universe.ConfirmAlertDialog
import com.milkyway.milkyway.util.DataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MyReportFragment : Fragment() {
    // 뷰모델 선언 (ktx 이용)
    private val myReportViewModel: MyReportViewModel by activityViewModels()
    private val cancelViewModel: CancelViewModel by activityViewModels()
    private lateinit var binding: FragmentMyReportBinding
    private lateinit var deleteCancelDialog: ConfirmAlertDialog

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
//        cancelAdapter = CancelAdapter(view.context, canceldatas)    // 아이템 바인딩 안한버전
        cancelAdapter = CancelAdapter()
        ingAdapter = IngAdapter(view.context, ingdatas)
        doneAdapter= DoneAdapter(view.context, donedatas)

        binding.rvMyreportCancel.adapter = cancelAdapter
        binding.rvMyreportIng.adapter = ingAdapter
        binding.rvMyreportDone.adapter = doneAdapter

//        binding.rvMyreportCancel.layoutManager = LinearLayoutManager(binding.rvMyreportCancel.context)    // 이것도 가능
        binding.rvMyreportCancel.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMyreportIng.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMyreportDone.layoutManager = LinearLayoutManager(view.context)

        cancelAdapter.onClickListener =  {
            cancelViewModel.setDeleteCancelClick()
            cancelViewModel.clickCafeId.value = cancelAdapter.clickItemCafeId
            // 여기서 리사이클러뷰의 어떤 아이템이 클릭 됬는지 알아냄
//            Log.e("삭제아이템클릭", cancelViewModel.deleteCancel.value.toString())
        }

//        myReportViewModel.RVCancel()
//        myReportViewModel.recyclerListData.observe(viewLifecycleOwner, Observer {
//            cancelAdapter.data = it   // 더미데이터 들어감
//            cancelAdapter.notifyDataSetChanged()
//        })

        setReportData(binding)
        observeItemClickDelete()
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
//                cancelAdapter.datas = it.cancel
                cancelAdapter.datas = it.cancel as MutableList<CancelReport>   // 서버데이터 들어감
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

    // 아이템 누르면 선택(true)되어 서버통신하고 다이얼로그 띄움
    private fun observeItemClickDelete() {
        deleteCancelDialog= ConfirmAlertDialog(requireContext(),4).create()
        cancelViewModel.deleteCancel.observe(
            viewLifecycleOwner, Observer { deleteCancel ->
                if (deleteCancel) {
                    requestDeleteCancelData()
                    Log.e("삭제팝업", deleteCancel.toString())
                    deleteCancelDialog.show {
                        cancelViewModel.setDeleteCancelClick()
                        cancelAdapter.notifyItemRemoved(cancelAdapter.clickItemPosition)
                        Log.e("삭제됨", deleteCancel.toString())
                    }
                }
                else{
                    deleteCancelDialog.dismiss()
                }
            })
    }

    private fun requestDeleteCancelData(){
        lifecycleScope.launch {
            DataStore(requireContext()).getToken.collect {
                cancelViewModel.requestDeleteCancel(it!!)
//                cancelViewModel.requestDeleteCancel("")

                Log.e("requestDeleteCancelData", cancelViewModel.clickCafeId.value!!.toString())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setReportData(binding)
        observeItemClickDelete()
    }
}