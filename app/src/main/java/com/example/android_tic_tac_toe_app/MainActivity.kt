package com.example.android_tic_tac_toe_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home) // Set the home layout

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener { // Navigate to FourTileActivity when button is clicked
            val intent = Intent(this@MainActivity, FourTileActivity::class.java)
            startActivity(intent)
        }
    }
}
