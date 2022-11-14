package com.evgenykuksov.moviebase.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.evgenykuksov.moviebase.ui.main.MainActivity
import kotlinx.coroutines.delay

internal class SplashScreenActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {}
        lifecycleScope.launchWhenStarted {
            delay(DELAY_START_MAIN)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }
    }

    companion object {

        private const val DELAY_START_MAIN = 2500L
    }
}