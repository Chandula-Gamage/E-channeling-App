package com.example.a24health

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var btnLabTest: Button
    private lateinit var btnBuyMedicine: Button
    private lateinit var btnFindDoctor: Button
    private lateinit var btnHealthArticles: Button
    private lateinit var btnOrderDetails: Button
    private lateinit var btnLogout: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize UI components
        tvWelcome = findViewById(R.id.titleHome)
        btnLabTest = findViewById(R.id.btnLabTest)
        btnBuyMedicine = findViewById(R.id.btnBuyMedicine)
        btnFindDoctor = findViewById(R.id.btnFindDoctor)
        btnHealthArticles = findViewById(R.id.btnHealthArticles)
        btnOrderDetails = findViewById(R.id.btnOrderDetails)
        btnLogout = findViewById(R.id.btnLogout)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Retrieve saved username
        val savedUsername = sharedPreferences.getString("Username", "User")
        tvWelcome.text = "Welcome, $savedUsername!"

        // Set onClickListeners for all buttons
        btnLabTest.setOnClickListener {
            startActivity(Intent(this, LabTestActivity::class.java))
        }

//        btnBuyMedicine.setOnClickListener {
//            startActivity(Intent(this, BuyMedicineActivity::class.java))
//        }

        btnFindDoctor.setOnClickListener {
            startActivity(Intent(this, FindDoctorActivity::class.java))
        }

        btnHealthArticles.setOnClickListener {
            startActivity(Intent(this, HealthArticleActivity::class.java))
        }

//        btnOrderDetails.setOnClickListener {
//            startActivity(Intent(this, OrderDetailsActivity::class.java))
//        }

        // Logout button clears session and redirects to login
        btnLogout.setOnClickListener {
            sharedPreferences.edit().apply {
                putBoolean("isLoggedIn", false)
                apply()
            }

            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Close HomeActivity
        }
    }
}