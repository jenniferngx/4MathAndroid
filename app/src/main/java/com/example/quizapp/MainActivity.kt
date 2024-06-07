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

class MainActivity : Activity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val secondActivityBtn: ImageButton = findViewById (R.id.secondActivityBtn)
        val thirdActivityBtn: ImageButton = findViewById (R.id.thirdActivityBtn)
        val fourthActivityBtn: ImageButton = findViewById (R.id.fourthActivityBtn)

//        val actionBar = supportActionBar
//        actionBar!!.title = "First Activity"

        secondActivityBtn.setOnClickListener{
            val intent = Intent(this, MultiDifficulty::class.java)
            startActivity(intent)
        }

        thirdActivityBtn.setOnClickListener{
            val intent = Intent(this, BlankDifficulty::class.java)
            startActivity(intent)
        }

        fourthActivityBtn.setOnClickListener{
            val intent = Intent(this, GridDifficulty::class.java)
            startActivity(intent)
        }

//        val myScore: TextView = findViewById(R.id.myScore)
//        myScore.text = "${testVariable.myScore}"
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
