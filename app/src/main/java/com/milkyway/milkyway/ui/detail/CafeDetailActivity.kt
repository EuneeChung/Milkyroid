package com.milkyway.milkyway.ui.detail

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.milkyway.milkyway.R
import com.milkyway.milkyway.data.model.CafeMenu
import com.milkyway.milkyway.databinding.ActivityCafeDetailBinding
import com.milkyway.milkyway.ui.modify.ModifyActivity
import com.milkyway.milkyway.util.DataStore
import com.milkyway.milkyway.util.Toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CafeDetailActivity : AppCompatActivity() {
    // 뷰모델 선언 (ktx 이용)
    // -> private val 변수명 : 해당ViewModel by viewModels() (In Activity)
    // -> private val 변수명 : 해당ViewModel by activityViewModels() (In Fragment)
    private val cafedetailViewModel: CafeDetailViewModel by viewModels()

    // 리사이클러뷰 적용
    val datas = mutableListOf<CafeMenu>()
    var num = 1 // 비동기 처리 때문에 일단 초기화
    var cafeid = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ->Activity이름Binding = DataBindingUitl.setContentView(this, R.layout.레이아웃이름) eg) ActivityNickNameBinding
        // ->Fragment이름Binding = DataBindingUitl.setContentView(this, R.layout.레이아웃이름) eg) FragmentNickNameBinding
        val binding: ActivityCafeDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_cafe_detail)

        // layout에서 <data>에 선언한 viewModel에 연결
        binding.viewModel = cafedetailViewModel
        binding.lifecycleOwner = this

        cafeid = intent.extras!!.getInt("cafeId")
        Log.d("cafeid", "${cafeid}")

        loading(binding)
        setMenuData(binding)
        universeAddCancel(binding)
        showDetailTime(binding)
        setTextBold(binding)
        setBack(binding)
        cellPhone(binding)
        webSite(binding)

        clickGoToModify(binding)
    }

    private fun loading(binding: ActivityCafeDetailBinding) {
        binding.imgLoading.playAnimation()
    }

    private fun setMenuData(binding: ActivityCafeDetailBinding) {
        // 어댑터에 context 객체를 파라미터로 전달
        val menuAdapter =
            MenuAdapter(this, datas)

        binding.rvDetailMenu.adapter = menuAdapter
        binding.rvDetailMenu.layoutManager = LinearLayoutManager(this)

        // 토큰값 얻어오며 서버요청
        lifecycleScope.launch {
            DataStore(this@CafeDetailActivity).getToken.collect {
                cafedetailViewModel.requestDetailData(it!!, cafeId = cafeid)
            }
        }
        cafedetailViewModel.recyclerListData.observe(this, Observer {
            menuAdapter.datas = it   // 서버데이터 들어감
            menuAdapter.notifyDataSetChanged()
        })

        cafedetailViewModel.cafeInfoData.observe(this, Observer {
//            binding.tvDetailCafe.text = cafedetailViewModel.tv_detail_cafename.value.toString() // xml에서 바인딩 썼을때(value로 접근해야함)
            binding.tvDetailCafe.text = it.cafeName
            binding.tvDetailLocation.text = it.cafeAddress
            binding.tvDetailTime2.text = it.businessHours
            binding.tvDetailCall.text = it.cafePhoneNum
            binding.tvDetailWeb.text = it.cafeLink

            binding.tvDetailUniverseCount.text = it.universeCount.toString()
            binding.tvDetailShowcount.text = it.universeCount.toString() + "명의 밀키들이 유니버스에 추가했어요"
            Log.d("확인", "${binding.tvDetailUniverseCount.text}")
            num = it.universeCount
            Log.d("카운트1", "$num")

            if (it.isUniversed == 0) {
                binding.btnDetailUniverse.setBackgroundResource(R.drawable.btn_universe)
                binding.tvDetailUniverseCount.setTextColor(getColor(R.color.gray_9a9792))
                binding.btnDetailUniverse.setOnClickListener {
                    requestAddUniverseData()
                    Toast.customToast("나의 유니버스에 추가되었습니다", this)
                    Log.d("추가", cafedetailViewModel.isSelected.value.toString())
                }
            }
            if (it.isUniversed == 1) {
                binding.btnDetailUniverse.setBackgroundResource(R.drawable.btn_universe_added_detail)
                binding.tvDetailUniverseCount.setTextColor(getColor(R.color.blue_3320a6))
                binding.tvDetailUniverseCount.typeface = ResourcesCompat.getFont(binding.tvDetailUniverseCount.context, R.font.roboto_bold)
                binding.btnDetailUniverse.setOnClickListener {
                    requestDeleteUniverseData()
                    Toast.customToast("나의 유니버스에서 삭제되었습니다", this)
                    Log.d("삭제", cafedetailViewModel.isSelected.value.toString())
                }
            }

            if (it.honeyTip.any { it == 1 }) {
                binding.tvDetailTip1.setTextColor(
                    ContextCompat.getColorStateList(this, R.color.blue_3320a6)
                )
                binding.tvDetailTip1.setBackgroundResource(R.drawable.border_blue_honeytip)
            }
            if (it.honeyTip.any { it == 2 }) {
                binding.tvDetailTip2.setTextColor(
                    ContextCompat.getColorStateList(this, R.color.blue_3320a6)
                )
                binding.tvDetailTip2.setBackgroundResource(R.drawable.border_blue_honeytip)
            }
            if (it.honeyTip.any { it == 3 }) {
                binding.tvDetailTip3.setTextColor(
                    ContextCompat.getColorStateList(this, R.color.blue_3320a6)
                )
                binding.tvDetailTip3.setBackgroundResource(R.drawable.border_blue_honeytip)
            }
            if (it.honeyTip.any { it == 4 }) {
                binding.tvDetailTip4.setTextColor(
                    ContextCompat.getColorStateList(this, R.color.blue_3320a6)
                )
                binding.tvDetailTip4.setBackgroundResource(R.drawable.border_blue_honeytip)
            }
            if (it.honeyTip.any { it == 5 }) {
                binding.tvDetailTip5.setTextColor(
                    ContextCompat.getColorStateList(this, R.color.blue_3320a6)
                )
                binding.tvDetailTip5.setBackgroundResource(R.drawable.border_blue_honeytip)
            }
            if (it.honeyTip.any { it == 6 }) {
                binding.tvDetailTip6.setTextColor(
                    ContextCompat.getColorStateList(this, R.color.blue_3320a6)
                )
                binding.tvDetailTip6.setBackgroundResource(R.drawable.border_blue_honeytip)
            }
        })

//        cafedetailViewModel.universeCount.observe(this, Observer {
//            binding.tvDetailUniverseCount.text = it.toString()
//            binding.tvDetailShowcount.text = it.toString() + "명의 밀키들이 유니버스에 추가했어요"
//            Log.d("확인", "${binding.tvDetailUniverseCount.text}")
//            num = it
//            Log.d("카운트1", "$num")
//        })

    }

    // 유니버스 추가,취소 -> 뷰모델에 있는 isSelected값을 관찰하여 유니버스 카운트 증감
    private fun universeAddCancel(binding: ActivityCafeDetailBinding) {
//        var num = cafedetailViewModel.universecount.value!!.toInt()    // 이런식으로 하면 값 안들어감(androidx.lifecycle.MutableLiveData@3042ba0)
        Log.d("카운트11", "$num")
        cafedetailViewModel.isSelected.observe(this, Observer { isSelected ->
            isSelected?.let {
                if (isSelected) {
                    num += 1
                    binding.btnDetailUniverse.setBackgroundResource(R.drawable.btn_universe_added_detail)
                    binding.tvDetailUniverseCount.setTextColor(getColor(R.color.blue_3320a6))
                    binding.tvDetailUniverseCount.typeface = ResourcesCompat.getFont(binding.tvDetailUniverseCount.context, R.font.roboto_bold)
                    Toast.customToast("나의 유니버스에 추가되었습니다", this)
                    Log.d("추가카운트", "$num")
                    binding.btnDetailUniverse.setOnClickListener{
                        requestDeleteUniverseData()
                    }
                } else {
                    num -= 1
                    binding.btnDetailUniverse.setBackgroundResource(R.drawable.btn_universe)
                    binding.tvDetailUniverseCount.setTextColor(getColor(R.color.gray_9a9792))
                    Toast.customToast("나의 유니버스에서 삭제되었습니다", this)
                    Log.d("삭제카운트", "$num")
                    binding.btnDetailUniverse.setOnClickListener{
                        requestAddUniverseData()
                    }
                }
                binding.tvDetailUniverseCount.text = num.toString()
                binding.tvDetailShowcount.text =
                    "${binding.tvDetailUniverseCount.text}" + "명의 밀키들이 유니버스에 추가했어요"
            }
        })
    }

    // 유니버스 삭제 서버통신
    private fun requestDeleteUniverseData() {
        cafedetailViewModel.isLoading()
        lifecycleScope.launch {
            DataStore(this@CafeDetailActivity).getToken.collect {
                cafedetailViewModel.requestDeleteUniverse(it!!, cafeid)
                Log.e(
                    "requestDeleteUniverse",
                    cafedetailViewModel.cafeId.value!!.toString()
                )
            }
        }
    }

    // 유니버스 추가 서버통신
    private fun requestAddUniverseData() {
        cafedetailViewModel.isLoading()
        lifecycleScope.launch {
            DataStore(this@CafeDetailActivity).getToken.collect {
                cafedetailViewModel.requestAddUniverse(it!!, cafeid)
                Log.e("requestAddUniverse", cafedetailViewModel.cafeId.value!!.toString())
            }
        }
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
    private fun setBack(binding: ActivityCafeDetailBinding) {
        binding.btnDetailBack.setOnClickListener { finish() }
    }

    // 정보수정요청 이동
    private fun clickGoToModify(binding: ActivityCafeDetailBinding) {
        binding.btnDetailFixrequest.setOnClickListener {
            val intent = Intent(this, ModifyActivity::class.java)
            intent.putExtra("cafeId", cafeid)
            startActivity(intent)
        }
    }

    // 전화 열기
    private fun cellPhone(binding: ActivityCafeDetailBinding){
        binding.tvDetailCall.setOnClickListener{
            val callintent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + binding.tvDetailCall.text))
            startActivity(callintent)
        }
    }

    // 웹사이트 연결
    private fun webSite(binding: ActivityCafeDetailBinding){
        binding.tvDetailWeb.setOnClickListener{
            val webintent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.tvDetailWeb.text.toString()))
            startActivity(webintent)
        }
    }
}
