package com.milkyway.milkyway.ui.nickname

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityNicknameBinding

class NicknameActivity : AppCompatActivity() {
    private val nicknameViewModel : NicknameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityNicknameBinding = DataBindingUtil.setContentView(this, R.layout.activity_nickname)
        binding.nicknameViewModel = nicknameViewModel
        binding.lifecycleOwner = this
    }
}