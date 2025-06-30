package com.example.a24health

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var edUsername: EditText
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var edConfirm: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize UI components
        edUsername = findViewById(R.id.editTextText)
        edEmail = findViewById(R.id.editTextText3)
        edPassword = findViewById(R.id.editTextText2)
        edConfirm = findViewById(R.id.editTextText4)
        btnRegister = findViewById(R.id.button2)
        tvLogin = findViewById(R.id.textView)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Register Button Click Event
        btnRegister.setOnClickListener {
            val username = edUsername.text.toString().trim()
            val email = edEmail.text.toString().trim()
            val password = edPassword.text.toString().trim()
            val confirm = edConfirm.text.toString().trim()

            when {
                username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty() -> {
                    Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
                }
                password != confirm -> {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // Save user credentials in SharedPreferences
                    sharedPreferences.edit().apply {
                        putString("Username", username)
                        putString("Password", password)
                        apply()
                    }

                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()

                    // Navigate to LoginActivity
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }

        // Login Text Click Event (Navigate to Login Page)
        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
