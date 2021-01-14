package com.milkyway.milkyway.ui.setting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.milkyway.milkyway.R
import com.milkyway.milkyway.ui.nickname.NicknameActivity
import com.milkyway.milkyway.util.DataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {
    lateinit var mytoken : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv_setting_nickname = view.findViewById<TextView>(R.id.tv_setting_nickname)

        lifecycleScope.launch {
            DataStore(requireContext()).getToken.collect {
                mytoken = it.toString()
            }
        }

        tv_setting_nickname.setOnClickListener {
            val intent = Intent(view.context, NicknameActivity::class.java)
            intent.putExtra("ACCESS", 1)
            intent.putExtra("TOKEN", mytoken)
            startActivity(intent)

        }
    }
}