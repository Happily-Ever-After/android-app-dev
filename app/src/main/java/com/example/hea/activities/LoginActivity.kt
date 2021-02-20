package com.example.hea.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hea.R
import com.example.hea.api.Endpoints
import com.example.hea.api.ServiceBuilder
import com.example.hea.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val back: Button= findViewById(R.id.btn_back)
        back.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btn_login: Button = findViewById(R.id.btn_submit_login)
        btn_login.setOnClickListener {
            val email: EditText = findViewById(R.id.login_email)
            val pwd: EditText = findViewById(R.id.login_password)
            var isSuccess = false

            val user = User()
            user.email = email.text.toString()
            user.password = pwd.text.toString()

            val api = ServiceBuilder.buildService(Endpoints::class.java)
            val req = api.login(user)

            req.enqueue(object: Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        isSuccess = true
//                        finish() // Move back
//                        var newlyCreatedDestination = response.body() // Use it or ignore it
                        Toast.makeText(this@LoginActivity, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@LoginActivity, "Incorrect Email or Password", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "User Does Not Exists", Toast.LENGTH_SHORT).show()
                }
            })

            if(isSuccess){
                val intent: Intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }
}