package com.milkyway.milkyway.ui.modify.request

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityRequestModificationsBindingImpl

class RequestModificationsActivity : AppCompatActivity() {
    private val requestModificationsViewModel : RequestModificationsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()

        isOver5WordsObserve()

    }

    private fun initBinding(){
        val binding:ActivityRequestModificationsBindingImpl =
            DataBindingUtil.setContentView(this, R.layout.activity_request_modifications)
        binding.vm=requestModificationsViewModel
        binding.lifecycleOwner=this
    }
    private fun isOver5WordsObserve(){
        requestModificationsViewModel.modifications.observe(this, Observer { modifications->
            requestModificationsViewModel._isActiveRequest.value = modifications.length >= 5
        })
    }
}