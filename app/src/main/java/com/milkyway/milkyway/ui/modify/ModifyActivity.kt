package com.milkyway.milkyway.ui.modify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityModifyBinding
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.ui.modify.dialog.DeleteFragmentDialog
import com.milkyway.milkyway.ui.modify.request.RequestModificationsActivity
import com.milkyway.milkyway.util.DataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ModifyActivity : AppCompatActivity() {
    private val modifyViewModel: ModifyDialogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        observeDeleteClick()

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
    private fun observeDeleteClick(){
        modifyViewModel.isDeleteClick.observe(this, Observer{ isDeleteClick->
            Log.e("isDeleteClick",isDeleteClick.toString())
            if(isDeleteClick) {
//                ConfirmAlertDialog(this,1).show()
            }
        })
    }

    private fun deleteLocation() {
        lifecycleScope.launch {
            DataStore(this@ModifyActivity).getToken.collect {

//                modifyViewModel.requestDeleteLocation(it!!,cafeId = 10,reason = )
            }
        }
    }


}