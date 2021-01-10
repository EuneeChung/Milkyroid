package com.milkyway.milkyway.ui.report.detail

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityCafeDetailBinding


class CafeDetailActivity : AppCompatActivity() {
    // 뷰모델 선언 (ktx 이용)
    // -> private val 변수명 : 해당ViewModel by viewModels() (In Activity)
    // -> private val 변수명 : 해당ViewModel by activityViewModels() (In Fragment)
    private val cafedetailViewModel : CafeDetailViewModel by viewModels()

    // 리사이클러뷰 적용
    lateinit var menuAdapter: MenuAdapter
    val datas = mutableListOf<MenuData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ->Activity이름Binding = DataBindingUitl.setContentView(this, R.layout.레이아웃이름) eg) ActivityNickNameBinding
        // ->Fragment이름Binding = DataBindingUitl.setContentView(this, R.layout.레이아웃이름) eg) FragmentNickNameBinding
        val binding = DataBindingUtil.setContentView<ActivityCafeDetailBinding>(this, R.layout.activity_cafe_detail)

        // layout에서 <data>에 선언한 viewModel에 연결
        binding.viewModel = cafedetailViewModel
        binding.lifecycleOwner = this

        setDetailData(binding)
        universeAddCancel(binding)
        showDetailTime(binding)
        setTextBold(binding)
        setBack(binding)
    }

    private fun setDetailData(binding: ActivityCafeDetailBinding) {
//        var datas: MutableList<MenuData> = mutableListOf<MenuData>()
//        val datass = MutableLiveData<MutableList<MenuData>>()

        // 어댑터에 context 객체를 파라미터로 전달 (더미)
        menuAdapter = MenuAdapter(this)
//        menuAdapter = MenuAdapter(view.context)   // 더미 프래그먼트일때
//        menuAdapter= MenuAdapter(view.context, datas) // 서버 프래그먼트

        binding.rvDetailMenu.adapter = menuAdapter
        binding.rvDetailMenu.layoutManager = LinearLayoutManager(this)

        cafedetailViewModel.recyclerListData.observe(this, Observer {
            menuAdapter.data = it   // 더미데이터 들어감
            menuAdapter.notifyDataSetChanged()
        })
    }


    var num = 0
    // 유니버스 추가,취소 -> 뷰모델에 있는 isSelected값을 관찰하여 유니버스 카운트 증감
    private fun universeAddCancel(binding: ActivityCafeDetailBinding) {
        cafedetailViewModel.isSelected.observe(this, Observer { isSelected ->
            isSelected?.let {
                if (isSelected) {
                    binding.tvDetailUniverseCount.text = num.toString()
                    num = "${binding.tvDetailUniverseCount.text}".toInt() - 1
                    binding.btnDetailUniverse.setBackgroundResource(R.drawable.btn_universe_added)
                } else {
                    binding.tvDetailUniverseCount.text = num.toString()
                    num = "${binding.tvDetailUniverseCount.text}".toInt() + 1
                    binding.btnDetailUniverse.setBackgroundResource(R.drawable.btn_universe)
                }
                binding.tvDetailShowcount.text =
                    "${binding.tvDetailUniverseCount.text}" + "명의 밀키들이 유니버스에 추가했어요"
            }
        })
    }

    // 운영시간 보기
    private fun showDetailTime(binding: ActivityCafeDetailBinding) {
        cafedetailViewModel.isSelected2.observe(this, Observer { isSelected2 ->
            isSelected2?.let {
                if (isSelected2) {
                    if (binding.tvDetailTime2.visibility == View.GONE) {
                        binding.btnDetailTime.setBackgroundResource(R.drawable.btn_close)
                        binding.tvDetailTime2.visibility = View.VISIBLE
                    } else if (binding.tvDetailTime2.visibility == View.VISIBLE) {
                        binding.btnDetailTime.setBackgroundResource(R.drawable.btn_open)
                        binding.tvDetailTime2.visibility = View.GONE
                    }
                }
            }
        })
    }

    // 특정 글자 굵게
    private fun setTextBold(binding: ActivityCafeDetailBinding) {
        val content = binding.tvDetailMsg.text.toString()
        val spannableString = SpannableString(content)

        val word = "카페 방문 시 한번 더 확인하시기를 추천드립니다."
        val start = content.indexOf(word)
        val end = start + word.length

        spannableString.setSpan(
            StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvDetailMsg.text = spannableString
    }

    // 뒤로가기 버튼 이벤트
    private fun setBack(binding: ActivityCafeDetailBinding){
        binding.btnDetailBack.setOnClickListener { finish() }
    }
}
