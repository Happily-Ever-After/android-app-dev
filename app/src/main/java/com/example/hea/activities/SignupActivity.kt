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

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val back: Button= findViewById(R.id.btn_back)
        back.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btn_signup: Button = findViewById(R.id.btn_submit_signup)
        btn_signup.setOnClickListener {
            print("Button clicked")
            val name: EditText = findViewById(R.id.name)
            val phno: EditText = findViewById(R.id.phno)
            val email: EditText = findViewById(R.id.email)
            val pwd: EditText = findViewById(R.id.password)

            val user = User()
            user.name = name.text.toString()
            user.phno = phno.text.toString()
            user.email = email.text.toString()
            user.password = pwd.text.toString()

            val intent: Intent = Intent(this, ProfileActivity::class.java)

            val service = ServiceBuilder.buildService(Endpoints::class.java)
            val request = service.signup(user)

            request.enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        print("Success")
                        finish() // Move back
//                        var newlyCreatedDestination = response.body() // Use it or ignore it
                        Toast.makeText(this@SignupActivity, "Successfully Registered", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    print("Failure")
                    Toast.makeText(this@SignupActivity, "User Already Exists", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}