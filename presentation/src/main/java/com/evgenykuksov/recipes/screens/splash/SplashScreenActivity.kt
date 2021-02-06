package com.evgenykuksov.recipes.screens.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.evgenykuksov.recipes.R
import com.evgenykuksov.recipes.base.BaseActivity
import com.evgenykuksov.recipes.screens.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : BaseActivity(R.layout.activity_splash_screen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            delay(2500)
            finish()
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }
    }
}