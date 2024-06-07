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

class SignInActivity : Activity() {
    lateinit var userMap: HashMap<String, String>
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        Paper.init(applicationContext)

        userMap = HashMap()
        userMap = Paper.book().read("userMap", HashMap())!!

        val signInBtn: Button = findViewById(R.id.signin_btn)
        val usernameInput: EditText = findViewById(R.id.username_input)
        val passwordInput: EditText = findViewById(R.id.password_input)
        val passwordIncorrect: TextView = findViewById(R.id.pw_incorrect)
        val userNotExist: TextView = findViewById(R.id.user_not_exist)
        val emptyInputAlert: TextView = findViewById(R.id.empty_input)

        val otherOptions: LinearLayout = findViewById(R.id.other_options)
        val signUpBtn: Button = findViewById(R.id.signup_btn)
//        val newUsername: EditText = findViewById(R.id.new_username_input)
//        val newPassword: EditText = findViewById(R.id.new_password_input)
//        val signUpNewBtn: Button = findViewById(R.id.signup_as_new_btn)
//        val backtoSignin: Button = findViewById(R.id.back_to_signin)
        val guestBtn: Button = findViewById(R.id.guest_btn)

        signInBtn.setOnClickListener{
            var username = usernameInput.text.toString().trim()
            var password = passwordInput.text.toString().trim()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val storedUserMap: HashMap<String, String>? = Paper.book().read("userMap", HashMap())
                if (storedUserMap?.containsKey(username) == true){//username exists
                    if (storedUserMap?.get(username) == password) {//correct password, start MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {//incorrect password
                        passwordIncorrect.visibility = View.VISIBLE
                    }
                } else {//username does not exist
                    signInBtn.visibility = View.GONE
                    usernameInput.visibility = View.GONE
                    passwordInput.visibility = View.GONE
                    otherOptions.visibility = View.GONE
                    userNotExist.visibility = View.VISIBLE
                }
            } else {
                emptyInputAlert.visibility = View.VISIBLE
            }
        }

        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        guestBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}







