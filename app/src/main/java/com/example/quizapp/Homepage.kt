package com.example.quizapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class Homepage: Activity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        val playBtn: ImageButton = findViewById (R.id.playBtn)

        playBtn.setOnClickListener{
            val intent = Intent(this, Intro1::class.java)
            startActivity(intent)
        }
    }
}


