package com.example.pract_3_2_hvorenkov

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText

class TV_SignIn : Activity() {

    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var login:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_sign_in)

        email=findViewById(R.id.email)
        password=findViewById(R.id.password)
        login=findViewById(R.id.login)

        login.setOnClickListener{
            if (email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty())
            {
                val intent=Intent(this,TV_5::class.java)
                startActivity(intent)
            }
            else
            {
                val alert=AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("У вас есть пустые поля")
                    .setPositiveButton("OK",null)
                    .create()
                    .show()
            }
        }


    }




}