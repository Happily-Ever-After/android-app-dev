package com.example.hea.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hea.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login: Button = findViewById(R.id.btn_login)
        login.setOnClickListener { openLogin() }

        val signup: Button = findViewById(R.id.btn_signup)
        signup.setOnClickListener { openSignup() }
    }

    private fun openLogin() {
        val intent: Intent = Intent(this, LoginActivity::class.java);
        startActivity(intent);
    }

    private fun openSignup() {
        val intent: Intent = Intent(this, SignupActivity::class.java);
        startActivity(intent);
    }
}