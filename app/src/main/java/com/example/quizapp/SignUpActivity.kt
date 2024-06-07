package com.example.quizapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import io.paperdb.Paper
import java.io.Serializable

class SignUpActivity : Activity() {
    lateinit var userMap: HashMap<String, String>

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        Paper.init(applicationContext)

        userMap = HashMap()
        userMap = Paper.book().read("userMap", HashMap())!!

        val newUsername: EditText = findViewById(R.id.new_username_input)
        val newPassword: EditText = findViewById(R.id.new_password_input)
        val signUpNewBtn: Button = findViewById(R.id.signup_as_new_btn)
        val backtoSignIn: Button = findViewById(R.id.back_to_signin)
        val emptyInputAlert: TextView = findViewById(R.id.empty_input)
        val userAlreadyExists: TextView = findViewById(R.id.choose_another)
        signUpNewBtn.setOnClickListener {
            var newUser = newUsername.text.toString().trim()
            var newPw = newPassword.text.toString().trim()
            if (newUser.isNotEmpty() && newPw.isNotEmpty()) {
                val storedUserMap: HashMap<String, String>? =
                    Paper.book().read("userMap", HashMap())
                if (storedUserMap!!.containsKey(newUser)){// user already exists, ask to choose another username
                    userAlreadyExists.visibility = View.VISIBLE
                } else{// sign up success
                    storedUserMap?.set(newUser, newPw)
                    Paper.book().write("userMap", storedUserMap!!)
                    userAlreadyExists.visibility = View.GONE
                    emptyInputAlert.visibility = View.GONE
                    signUpNewBtn.visibility = View.GONE
                    backtoSignIn.visibility = View.VISIBLE
                }
            } else {
                emptyInputAlert.visibility = View.VISIBLE
            }
        }

        backtoSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}






