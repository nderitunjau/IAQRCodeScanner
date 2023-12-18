package com.gdff.iaqrcodescanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 5000 // 5 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            // Start your next activity after the specified time
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close this activity so it won't appear in the back stack
        }, SPLASH_TIME_OUT)
    }
}