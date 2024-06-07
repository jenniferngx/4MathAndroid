package com.example.quizapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class BlankDifficulty : Activity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.difficulty)
        val easy: ImageButton = findViewById (R.id.easy)
        val medium: ImageButton = findViewById (R.id.medium)
        val master: ImageButton = findViewById (R.id.master)

//        val actionBar = supportActionBar
//        actionBar!!.title = "First Activity"

        easy.setOnClickListener{
            val intent = Intent(this, BlankEasy::class.java)
            startActivity(intent)
        }

        medium.setOnClickListener{
            val intent = Intent(this, BlankActivity::class.java)
            startActivity(intent)
        }

        master.setOnClickListener{
            val intent = Intent(this, BlankHard::class.java)
            startActivity(intent)
        }

//        val myScore: TextView = findViewById(R.id.myScore)
//        myScore.text = "${testVariable.myScore}"
    }
}