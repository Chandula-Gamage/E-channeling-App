package com.example.a24health

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LabTestDetailsActivity : AppCompatActivity() {

    private lateinit var textViewLocation: TextView
    private lateinit var textViewTestType: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var buttonConfirm: Button
    private lateinit var buttonEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lab_test_details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI components
        textViewLocation = findViewById(R.id.textViewLocation)
        textViewTestType = findViewById(R.id.textViewTestType)
        textViewPrice = findViewById(R.id.textViewPrice)
        buttonConfirm = findViewById(R.id.buttonConfirm)
        buttonEdit = findViewById(R.id.buttonEdit)

        // Retrieve data from Intent
        val location = intent.getStringExtra("LOCATION") ?: "N/A"
        val testType = intent.getStringExtra("TEST_TYPE") ?: "N/A"
        val price = intent.getStringExtra("PRICE") ?: "N/A"

        // Display the data
        textViewLocation.text = "Location: $location"
        textViewTestType.text = "Test Type: $testType"
        textViewPrice.text = "Price: $price"

        // Confirm Button Logic
        buttonConfirm.setOnClickListener {
            android.widget.Toast.makeText(this, "Lab test confirmed! Location: $location, Test Type: $testType, Price: $price", android.widget.Toast.LENGTH_LONG).show()
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Clear the activity stack
            startActivity(intent)
            finish()
        }

        // Edit Button Logic
        buttonEdit.setOnClickListener {
            finish() // Navigate back to LabTestActivity to edit the selections
        }
    }
}