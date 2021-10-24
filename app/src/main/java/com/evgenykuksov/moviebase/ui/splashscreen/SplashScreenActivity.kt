package com.evgenykuksov.moviebase.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evgenykuksov.core.extensions.launchWhenStarted
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.ui.main.MainActivity
import kotlinx.coroutines.delay

class SplashScreenActivity : AppCompatActivity(R.layout.activity_splash_screen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchWhenStarted {
            delay(DELAY_START_MAIN)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }
    }

    companion object {

        private const val DELAY_START_MAIN = 2500L
    }
}