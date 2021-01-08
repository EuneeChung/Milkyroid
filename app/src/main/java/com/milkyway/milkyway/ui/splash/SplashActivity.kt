package com.milkyway.milkyway.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivitySplashBinding
import com.milkyway.milkyway.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private val splashViewModel : SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this

        startAnimation(binding)
        setLottieListener(binding)
        setObserve()
    }

    private fun startAnimation(binding : ActivitySplashBinding) {
        binding.lottieSplash.playAnimation()
    }

    private fun setLottieListener(binding : ActivitySplashBinding) {
        binding.lottieSplash.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                splashViewModel.signIn("ad4908b2731efc43","1234")
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
            }
        })
    }

    private fun setObserve() {
        splashViewModel.isSignIn.observe(this, Observer{ isSignIn->
            isSignIn?.let { if(isSignIn) startActivity(Intent(this, MainActivity::class.java))
            }
        })
    }
}