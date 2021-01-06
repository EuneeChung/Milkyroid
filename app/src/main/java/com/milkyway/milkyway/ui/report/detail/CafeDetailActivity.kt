package com.milkyway.milkyway.ui.report.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.milkyway.R
import org.w3c.dom.Text

class CafeDetailActivity : AppCompatActivity() {
    //리사이클러뷰 적용
    lateinit var menuAdapter: MenuAdapter
    val datas = mutableListOf<MenuData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_detail)

//        var datas: MutableList<MenuData> = mutableListOf<MenuData>()

        val btn_detail_universe = this.findViewById<ImageView>(R.id.btn_detail_universe)
        val tv_detail_universe_count = this.findViewById<TextView>(R.id.tv_detail_universe_count)
        val tv_detail_showcount = this.findViewById<TextView>(R.id.tv_detail_showcount)

        btn_detail_universe.isSelected = false

        btn_detail_universe.setOnClickListener{
            if("${tv_detail_universe_count.text}".toInt() <= 0) {
                Log.d("선택", "X")
            }
            else{
                if (btn_detail_universe.isSelected == true) {
                    tv_detail_universe_count.text =
                        ("${tv_detail_universe_count.text}".toInt() - 1).toString()
                    btn_detail_universe.isSelected = false
                }
                else if (btn_detail_universe.isSelected == false) {
                    tv_detail_universe_count.text =
                        ("${tv_detail_universe_count.text}".toInt() + 1).toString()
                    btn_detail_universe.isSelected = true
                }
            }
            tv_detail_showcount.text = "${tv_detail_universe_count.text}" + "명의 밀키들이 유니버스에 추가했어요"
        }

        val btn_detail_time = this.findViewById<ImageView>(R.id.btn_detail_time)
        val tv_detail_time2 = this.findViewById<TextView>(R.id.tv_detail_time2)

        btn_detail_time.setOnClickListener{
            if(tv_detail_time2.getVisibility() == View.GONE) {
                btn_detail_time.setBackgroundResource(R.drawable.btn_close)
                tv_detail_time2.setVisibility(View.VISIBLE)
            }
            else if(tv_detail_time2.getVisibility() == View.VISIBLE){
                btn_detail_time.setBackgroundResource(R.drawable.btn_open)
                tv_detail_time2.setVisibility(View.GONE)
            }
        }



        // 서버 연결(아직 더미)
//        menuAdapter= MenuAdapter(view.context, datas)
        menuAdapter =
            MenuAdapter(this)

        // 어댑터에 context 객체를 파라미터로 전달 (더미)
//        profileAdapter = ProfileAdapter(view.context)

        val rv_detailmenu = this.findViewById<RecyclerView>(R.id.rv_detail_menu)
        rv_detailmenu.adapter = menuAdapter
        rv_detailmenu.layoutManager = LinearLayoutManager(this)

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
}
