package com.evgenykuksov.moviebase.screens.splash

import android.content.Intent
import android.os.Bundle
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import com.evgenykuksov.moviebase.extansions.launchWhenStarted
import com.evgenykuksov.moviebase.screens.main.MainActivity
import kotlinx.coroutines.delay

class SplashScreenActivity : BaseActivity(R.layout.activity_splash_screen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchWhenStarted {
            delay(DELAY_START_MAIN)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun initWidgets() {}

    override fun observeState() {}

    override fun observeSingleEffect() {}
}

private const val DELAY_START_MAIN = 2500L