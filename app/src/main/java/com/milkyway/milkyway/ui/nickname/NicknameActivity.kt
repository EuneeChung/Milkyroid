package com.milkyway.milkyway.ui.nickname

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityNicknameBinding
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.util.DataStore
import com.milkyway.milkyway.util.UUID
import kotlinx.coroutines.launch

class NicknameActivity : AppCompatActivity() {
    private val nicknameViewModel : NicknameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityNicknameBinding = DataBindingUtil.setContentView(this, R.layout.activity_nickname)
        binding.nicknameViewModel = nicknameViewModel
        binding.lifecycleOwner = this

        setClickListener(binding)
        setObserve()
    }

    private fun setClickListener(binding : ActivityNicknameBinding) {
        binding.btnSignUp.setOnClickListener{
            nicknameViewModel.signUp(UUID.uuid(this@NicknameActivity))
        }
    }

    private fun setObserve() {
        nicknameViewModel.isSignUp.observe(this, Observer{ isSignUp->
            isSignUp?.let {
                if(isSignUp) {
                    lifecycleScope.launch {
                        DataStore(this@NicknameActivity).setNickname(nicknameViewModel.nickname.value!!)
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        })
    }
}