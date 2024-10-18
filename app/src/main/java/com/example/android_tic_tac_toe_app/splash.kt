package com.example.android_tic_tac_toe_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //        getSupportActionBar().hide();
        Handler().postDelayed({
            val intent = Intent(this@splash, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}

