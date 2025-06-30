package com.example.a24health

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)

        // Set the content insets to handle system UI like the status bar and navigation bar.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the TextViews to display the appointment summary
        val textViewDoctorName = findViewById<TextView>(R.id.textViewDoctorName)
        val textViewDoctorSpecialization = findViewById<TextView>(R.id.textViewDoctorSpecialization)
        val textViewFullName = findViewById<TextView>(R.id.textViewFullName)
        val textViewContactNumber = findViewById<TextView>(R.id.textViewContactNumber)
        val textViewAddress = findViewById<TextView>(R.id.textViewAddress)
        val textViewEmail = findViewById<TextView>(R.id.textViewEmail)
        val textViewDate = findViewById<TextView>(R.id.textViewDate)
        val textViewTime = findViewById<TextView>(R.id.textViewTime)
        val textViewConsultationFee = findViewById<TextView>(R.id.textViewConsultationFee)

        // Retrieve the data passed from BookAppoinmentActivity
        val doctorName = intent.getStringExtra("doctorName") ?: "N/A"
        val doctorSpecialization = intent.getStringExtra("doctorSpecialization") ?: "N/A"
        val fullName = intent.getStringExtra("fullName") ?: "N/A"
        val contactNumber = intent.getStringExtra("contactNumber") ?: "N/A"
        val address = intent.getStringExtra("address") ?: "N/A"
        val email = intent.getStringExtra("email") ?: "N/A"
        val date = intent.getStringExtra("date") ?: "N/A"
        val time = intent.getStringExtra("time") ?: "N/A"
        val consultationFee = intent.getIntExtra("consultationFee", 0)

        // Display the data in the TextViews
        textViewDoctorName.text = "Doctor Name: $doctorName"
        textViewDoctorSpecialization.text = "Specialization: $doctorSpecialization"
        textViewFullName.text = "Full Name: $fullName"
        textViewContactNumber.text = "Contact Number: $contactNumber"
        textViewAddress.text = "Address: $address"
        textViewEmail.text = "Email: $email"
        textViewDate.text = "Date: $date"
        textViewTime.text = "Time: $time"
        textViewConsultationFee.text = "Consultation Fee: Rs.$consultationFee.00"

        // Edit Button Logic
        val buttonEdit = findViewById<Button>(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            // Create an Intent to return to BookAppoinmentActivity with the current details
            val intent = Intent(this, BookAppoinmentActivity::class.java)
            intent.putExtra("fullName", fullName)
            intent.putExtra("contactNumber", contactNumber)
            intent.putExtra("address", address)
            intent.putExtra("email", email)
            intent.putExtra("date", date)
            intent.putExtra("time", time)
            intent.putExtra("doctorName", doctorName)
            intent.putExtra("doctorSpecialization", doctorSpecialization)
            intent.putExtra("consultationFee", consultationFee)
            intent.putExtra("isEditing", true) // Flag to indicate we're editing
            startActivity(intent)
            finish() // Close ConfirmationActivity
        }

        // Confirm Button Logic
        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)
        buttonConfirm.setOnClickListener {
            // Show a confirmation message
            Toast.makeText(this, "Appointment Confirmed!", Toast.LENGTH_LONG).show()

            // Navigate to HomeActivity and clear the activity stack
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Close ConfirmationActivity
        }

        // Back Button Logic
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish() // Close this activity and return to the previous one
        }
    }
}