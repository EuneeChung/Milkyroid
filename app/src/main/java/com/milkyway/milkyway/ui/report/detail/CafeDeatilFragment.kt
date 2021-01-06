package com.milkyway.milkyway.ui.report.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R

class CafeDetailFragment : Fragment() {
    //리사이클러뷰 적용
    lateinit var menuAdapter: MenuAdapter
    val datas = mutableListOf<MenuData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cafe_deatil, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var datas: MutableList<MenuData> = mutableListOf<MenuData>()

        // 서버 연결(아직 더미)
//        menuAdapter= MenuAdapter(view.context, datas)
        menuAdapter=
            MenuAdapter(view.context)

        // 어댑터에 context 객체를 파라미터로 전달 (더미)
//        profileAdapter = ProfileAdapter(view.context)

        val rv_detailmenu = view.findViewById<RecyclerView>(R.id.rv_detail_menu)
        rv_detailmenu.adapter = menuAdapter
        rv_detailmenu.layoutManager = LinearLayoutManager(view.context)

        // (더미)
        menuAdapter.data = mutableListOf(
            MenuData(
                "라떼라떼",
                "4,000원",
                "두유"
            ),
            MenuData(
                "모카모카",
                "5,000원",
                "두유22"
            ),
            MenuData(
                "유기농녹차맛두유",
                "5,000원",
                "두유33"
            )
        )

        // Adapter에 데이터 갱신 알려줌
        menuAdapter.notifyDataSetChanged()
    }

//
//        // 서버 요청
//        PracticeServiceImpl.service.requestPractice(
//        ).enqueue(object : Callback<ResponsePractice> {
//            override fun onFailure(call: Call<ResponsePractice>, t: Throwable) {
//                Log.d("연습용 서버 연결 실패", "${t}")
//            }
//
//            override fun onResponse(
//                    call: Call<ResponsePractice>,
//                    response: Response<ResponsePractice>
//            ) {
//                // 통신 성공
//                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
//                    Log.d("받아온 데이터 ", response.body()!!.data.toString())
//
//                    var i: Int = 0
//                    for (i in 0 until response.body()!!.data.size) {
//
//                        datas.apply {
//                            add(
//                                    ProfileData(
//                                            email = "${response.body()!!.data[i].email}",
//                                            first_name = "${response.body()!!.data[i].first_name}",
//                                            avatar = "${response.body()!!.data[i].avatar}"
//                                    )
//                            )
//                        }
//                        profileAdapter.notifyDataSetChanged()
//                    }
//                } else {
//                    Log.d("연습용 서버 연결 실패2", "${response.message()}")
//                }
//            }
//        })


}
