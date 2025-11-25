package com.example.pract_3_2_hvorenkov

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer

class SplashScreen : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timer = object : CountDownTimer(3000,1000)
        {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val intent= Intent(this@SplashScreen,TV_SignIn::class.java)
                startActivity(intent)
                finish()
            }
        }
        timer.start()
    }
}