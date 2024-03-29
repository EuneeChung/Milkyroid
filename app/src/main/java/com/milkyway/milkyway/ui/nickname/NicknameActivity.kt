package com.milkyway.milkyway.ui.nickname

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityNicknameBinding
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.util.DataStore
import com.milkyway.milkyway.util.UUID
import com.milkyway.milkyway.util.startActivity
import kotlinx.coroutines.launch

class NicknameActivity : AppCompatActivity() {
    private val nicknameViewModel : NicknameViewModel by viewModels()
    private val dataStore = DataStore(this)
    var state = 0
    var access = 0
    private var mytoken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityNicknameBinding = DataBindingUtil.setContentView(this, R.layout.activity_nickname)
        binding.nicknameViewModel = nicknameViewModel
        binding.lifecycleOwner = this

        if (intent.hasExtra("ACCESS") && intent.hasExtra("TOKEN")) {
            access = intent.extras!!.getInt("ACCESS")
            mytoken = intent.extras!!.getString("TOKEN").toString()
        } else {
            Log.d("state", "처음부터 시작한 경우")
        }

        setClickListener(binding)
        setObserve()
        setStatusBarColor(this@NicknameActivity)
    }

    private fun setClickListener(binding : ActivityNicknameBinding) {
        binding.btnSignUp.setOnClickListener{
            if(access != state){
                nicknameViewModel.changeNickname(token = mytoken, newNickName = nicknameViewModel.nickname.toString())
            }
            else{
                nicknameViewModel.signUp(UUID.uuid(this@NicknameActivity))
            }
        }
    }

    private fun setObserve() {
        nicknameViewModel.isSignUp.observe(this, Observer{ isSignUp->
            isSignUp?.let {
                if(isSignUp) {
                    lifecycleScope.launch {
                        dataStore.setNickname(nicknameViewModel.nickname.value!!)
                    }
                    startActivity<MainActivity>()
                }
            }
        })

        nicknameViewModel.token.observe(this, Observer{ token ->
            token?.let {
                lifecycleScope.launch {
                    dataStore.setToken(token)
                }
            }
        })
    }

    private fun setStatusBarColor(context: Activity) {
        val window : Window = context.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(context, R.color.white_f6f5f2)
    }
}