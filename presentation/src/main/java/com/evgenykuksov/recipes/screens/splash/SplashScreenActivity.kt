package com.evgenykuksov.recipes.screens.splash

import android.content.Intent
import android.os.Bundle
import com.evgenykuksov.recipes.R
import com.evgenykuksov.recipes.base.BaseActivity
import com.evgenykuksov.recipes.screens.main.MainActivity

class SplashScreenActivity : BaseActivity(R.layout.activity_splash_screen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}