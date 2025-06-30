package com.example.a24health

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var edUsername: EditText
    private lateinit var edPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        edUsername = findViewById(R.id.editTextText)
        edPassword = findViewById(R.id.editTextText2)
        btnLogin = findViewById(R.id.button2)
        tvRegister = findViewById(R.id.textView3)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Login Button Click Event
        btnLogin.setOnClickListener {
            val username = edUsername.text.toString().trim()
            val password = edPassword.text.toString().trim()

            // Retrieve stored credentials
            val savedUsername = sharedPreferences.getString("Username", null)
            val savedPassword = sharedPreferences.getString("Password", null)

            when {
                username.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
                }
                savedUsername == null -> {
                    Toast.makeText(this, "You are not registered! Please register.", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, RegisterActivity::class.java))
                    finish()
                }
                username == savedUsername && password == savedPassword -> {
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                    // Save login state
                    sharedPreferences.edit().apply {
                        putBoolean("isLoggedIn", true)
                        apply()
                    }

                    // Navigate to HomeActivity
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                else -> {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Register Text Click Event (Navigate to Register Page)
        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}
