package com.milkyway.milkyway.ui.modify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityModifyBinding
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.ui.modify.dialog.DeleteFragmentDialog
import com.milkyway.milkyway.ui.modify.request.RequestModificationsActivity

class ModifyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

    }
    private fun initBinding(){
        val binding: ActivityModifyBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_modify)
        binding.lifecycleOwner=this
        setTopBar(binding)
        clickBtn(binding)
    }
    private fun setTopBar(binding: ActivityModifyBinding){
        binding.topBarTxt="정보 수정 요청"
        binding.topbarModify.imgBack.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun clickBtn(binding: ActivityModifyBinding){
        binding.btnModifyInformation.setOnClickListener{
            val intent= Intent(this,RequestModificationsActivity::class.java)
            startActivity(intent)
        }
        binding.btnDeleteLocation.setOnClickListener {
            val deleteFragmentDialog=DeleteFragmentDialog().show(
                supportFragmentManager,"deleteFragmentDialog"
            )
        }
    }


}