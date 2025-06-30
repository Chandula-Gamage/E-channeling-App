package com.example.a24health

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BookAppoinmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_appoinment)

        // Set the content insets to handle system UI like the status bar and navigation bar.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the EditText, Spinner, and TextView views
        val editTextFullName = findViewById<EditText>(R.id.editTextText)
        val editTextContactNumber = findViewById<EditText>(R.id.editTextText2)
        val editTextAddress = findViewById<EditText>(R.id.editTextText3)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextDate = findViewById<EditText>(R.id.editTextDate)
        val spinnerDate = findViewById<Spinner>(R.id.spinnerDate)
        val editTextTime = findViewById<EditText>(R.id.editTextTime)
        val spinnerTime = findViewById<Spinner>(R.id.spinnerTime)
        val textViewConsultationFee = findViewById<TextView>(R.id.textViewConsultationFee)

        // Retrieve the doctor details and consultation fee from the Intent
        val doctorName = intent.getStringExtra("doctorName") ?: "N/A"
        val doctorSpecialization = intent.getStringExtra("doctorSpecialization") ?: "N/A"
        val consultationFee = intent.getIntExtra("consultationFee", 0)
        textViewConsultationFee.text = "Consultation Fee: Rs.$consultationFee.00"

        // Populate the Date Spinner with the next 7 days
        val dateList = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        for (i in 0 until 7) {
            dateList.add(dateFormat.format(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        val dateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateList)
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDate.adapter = dateAdapter

        // Update the EditText when a date is selected
        spinnerDate.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedDate = parent.getItemAtPosition(position).toString()
                editTextDate.setText(selectedDate)
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {
                // Do nothing
            }
        })

        // Populate the Time Spinner with hourly slots from 9:00 AM to 5:00 PM
        val timeList = mutableListOf<String>()
        for (hour in 9..17) { // 9 AM to 5 PM
            val hourIn12Format = if (hour % 12 == 0) 12 else hour % 12
            val amPm = if (hour < 12) "AM" else "PM"
            timeList.add(String.format("%d:00 %s", hourIn12Format, amPm))
        }
        val timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeList)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTime.adapter = timeAdapter

        // Update the EditText when a time is selected
        spinnerTime.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedTime = parent.getItemAtPosition(position).toString()
                editTextTime.setText(selectedTime)
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {
                // Do nothing
            }
        })

        // Check if we're returning from ConfirmationActivity to edit the details
        val isEditing = intent.getBooleanExtra("isEditing", false)
        if (isEditing) {
            // Pre-fill the fields with the existing details
            val fullName = intent.getStringExtra("fullName") ?: ""
            val contactNumber = intent.getStringExtra("contactNumber") ?: ""
            val address = intent.getStringExtra("address") ?: ""
            val email = intent.getStringExtra("email") ?: ""
            val date = intent.getStringExtra("date") ?: ""
            val time = intent.getStringExtra("time") ?: ""

            editTextFullName.setText(fullName)
            editTextContactNumber.setText(contactNumber)
            editTextAddress.setText(address)
            editTextEmail.setText(email)
            editTextDate.setText(date)
            editTextTime.setText(time)

            // Set the spinner selections
            val datePosition = dateList.indexOf(date)
            if (datePosition >= 0) {
                spinnerDate.setSelection(datePosition)
            }

            val timePosition = timeList.indexOf(time)
            if (timePosition >= 0) {
                spinnerTime.setSelection(timePosition)
            }
        }

        // Next Button Logic
        val buttonNext = findViewById<Button>(R.id.button2)
        buttonNext.setOnClickListener {
            val fullName = editTextFullName.text.toString()
            val contactNumber = editTextContactNumber.text.toString()
            val address = editTextAddress.text.toString()
            val email = editTextEmail.text.toString()
            val date = editTextDate.text.toString()
            val time = editTextTime.text.toString()

            // Validate that all fields are filled
            if (fullName.isEmpty() || contactNumber.isEmpty() || address.isEmpty() || email.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Create an Intent to start the ConfirmationActivity
                val intent = Intent(this, ConfirmationActivity::class.java)
                // Pass the user details, doctor details, and consultation fee to the next activity
                intent.putExtra("fullName", fullName)
                intent.putExtra("contactNumber", contactNumber)
                intent.putExtra("address", address)
                intent.putExtra("email", email)
                intent.putExtra("date", date)
                intent.putExtra("time", time)
                intent.putExtra("doctorName", doctorName)
                intent.putExtra("doctorSpecialization", doctorSpecialization)
                intent.putExtra("consultationFee", consultationFee)
                startActivity(intent)
            }
        }

        // Back Button Logic
        val buttonBack = findViewById<Button>(R.id.bookappoinmentbackbutton)
        buttonBack.setOnClickListener {
            // Navigate back to the previous activity
            finish()
        }
    }
}