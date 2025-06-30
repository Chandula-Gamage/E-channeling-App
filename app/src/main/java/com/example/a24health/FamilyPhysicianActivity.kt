package com.example.a24health

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FamilyPhysicianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_family_physician)

        // Set the content insets to handle system UI like the status bar and navigation bar.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the doctor specialization (title) from the Intent
        val doctorSpecialization = intent.getStringExtra("title") ?: "Family Physician"

        // Update the TextView with the doctor specialization
        val textViewDoctorDetailTitle = findViewById<TextView>(R.id.textViewDoctorDetailTitle)
        textViewDoctorDetailTitle.text = doctorSpecialization

        // Set the consultation fee dynamically
        val textViewConsultationFee = findViewById<TextView>(R.id.textViewConsultationFee)
        val consultationFee = 1000 // This could be fetched from a database or API
        textViewConsultationFee.text = "Consultation Fee: Rs.$consultationFee.00"

        // Doctor details to pass
        val doctorName = "Dr. Michael Brown"

        // Book Appointment Button Logic
        val btnBookAppointment = findViewById<Button>(R.id.btn_book_appointment)
        btnBookAppointment.setOnClickListener {
            // Navigate to BookAppoinmentActivity and pass the doctor details and consultation fee
            val intent = Intent(this, BookAppoinmentActivity::class.java)
            intent.putExtra("doctorName", doctorName)
            intent.putExtra("doctorSpecialization", doctorSpecialization) // Use the received specialization
            intent.putExtra("consultationFee", consultationFee)
            startActivity(intent)
        }

        // Back Button Logic
        val buttonBack = findViewById<Button>(R.id.btnLogout)
        buttonBack.setOnClickListener {
            finish() // Close this activity and return to the previous one
        }
    }
}