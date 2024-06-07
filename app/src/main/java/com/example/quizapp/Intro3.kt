package com.example.quizapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class Intro3: Activity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_3)

        val skipBtn: Button = findViewById (R.id.skip_button)
        val nextBtn: Button = findViewById (R.id.intro_1_next)

        skipBtn.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        nextBtn.setOnClickListener{
            val intent = Intent(this, Intro4::class.java)
            startActivity(intent)
        }

    }
}