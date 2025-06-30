package com.example.a24health

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FindDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge display
        setContentView(R.layout.activity_find_doctor)

        // Set the content insets to handle system UI like the status bar and navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Family Physician Card
        val familyPhysician = findViewById<CardView>(R.id.cardFamilyPhysician)
        familyPhysician.setOnClickListener {
            navigateToFamilyPhysicianActivity("Family Physicians")
        }

        // Back Card
        val backCard = findViewById<CardView>(R.id.cardLogout)
        backCard.setOnClickListener {
            finish() // Close this activity and return to the previous one (e.g., HomeActivity)
        }
    }

    private fun navigateToFamilyPhysicianActivity(title: String) {
        val intent = Intent(this, FamilyPhysicianActivity::class.java)
        intent.putExtra("title", title) // Pass the doctor specialization
        startActivity(intent)
    }
}