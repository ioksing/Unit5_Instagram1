package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (ParseUser.getCurrentUser() != null) {
            goToMain()
        }

        findViewById<Button>(R.id.btnLogin).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_Username).text.toString()
            val password = findViewById<EditText>(R.id.et_Password).text.toString()
            loginUser(username, password)
        }

        findViewById<Button>(R.id.btnSignup).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_Username).text.toString()
            val password = findViewById<EditText>(R.id.et_Password).text.toString()
            signUp(username, password)
        }
    }

    private fun loginUser(username: String, password: String){
        ParseUser.logInInBackground(username, password, ({
            user, e ->
            if (user != null){
                Log.i(TAG, "successfully Logged in user")
                goToMain()
            } else {
                e.printStackTrace()
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun signUp(username: String, password: String) {
        val user = ParseUser()

        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground{e ->
            if (e == null) {

                // Go to main Activity
                goToMain()
            } else {
                e.printStackTrace()
            }
        }
    }

    private fun goToMain(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    companion object {
        const val TAG = "LoginActivity"
    }
}