package com.example.sikarma

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_SCREEN = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splashscreen)

        goToMainActivity()
    }

    private fun goToMainActivity() {
        Handler().postDelayed({
            val i = Intent(this@SplashscreenActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_SCREEN.toLong())
    }
}