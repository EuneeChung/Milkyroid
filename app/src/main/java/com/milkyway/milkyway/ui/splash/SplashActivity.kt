package com.milkyway.milkyway.ui.splash

import android.animation.Animator
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.milkyway.milkyway.R
import com.milkyway.milkyway.databinding.ActivitySplashBinding
import com.milkyway.milkyway.ui.main.MainActivity
import com.milkyway.milkyway.ui.nickname.NicknameActivity
import com.milkyway.milkyway.util.DataStore
import com.milkyway.milkyway.util.UUID
import com.milkyway.milkyway.util.startActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val splashViewModel : SplashViewModel by viewModels()
    private val dataStore = DataStore(this)

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
                lifecycleScope.launch {
                    dataStore.getNickname.collect {
                        splashViewModel.signIn(UUID.uuid(this@SplashActivity), it!!)
                    }
                }
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
            }
        })
    }

    private fun setObserve() {
        splashViewModel.isSignIn.observe(this, Observer { isSignIn ->
            isSignIn?.let {
                if (isSignIn) startActivity<MainActivity>()
                else startActivity<NicknameActivity>()
            }
        })

        splashViewModel.token.observe(this, Observer{ token ->
            token?.let {
                lifecycleScope.launch {
                    dataStore.setToken(token)
                }
            }
        })
    }
}