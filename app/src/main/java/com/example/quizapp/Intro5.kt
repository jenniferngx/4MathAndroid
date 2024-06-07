package com.example.quizapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.quizapp.R.*

class Intro5: Activity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_5)

        val nextBtn: Button = findViewById (R.id.intro_1_next)

        nextBtn.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}