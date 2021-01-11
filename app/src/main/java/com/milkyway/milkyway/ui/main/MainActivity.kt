package com.milkyway.milkyway.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.lifecycleOwner=this

        initBottomNavigation(binding)

    }

    private fun initBottomNavigation(binding: ActivityMainBinding) {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.itemIconTintList=null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.fragment_cafe_report)
        fragment?.onActivityResult(requestCode, resultCode, data)
    }
}