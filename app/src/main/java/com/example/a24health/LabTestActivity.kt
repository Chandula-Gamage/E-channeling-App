package com.example.a24health

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LabTestActivity : AppCompatActivity() {

    private lateinit var spinnerLocation: Spinner
    private lateinit var spinnerTestType: Spinner
    private lateinit var editTextPrice: EditText
    private lateinit var buttonNext: Button
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lab_test)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI components
        spinnerLocation = findViewById(R.id.spinnerLocation)
        spinnerTestType = findViewById(R.id.spinnerTestType)
        editTextPrice = findViewById(R.id.editTextPrice)
        buttonNext = findViewById(R.id.buttonNext)
        buttonBack = findViewById(R.id.buttonBack)

        // Populate Location Spinner
        val locations = arrayOf("Select location", "Malabe", "Kaduwela", "Mount Lavinia", "Moratuwa")
        val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locations)
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLocation.adapter = locationAdapter

        // Populate Test Type Spinner
        val testTypes = arrayOf("Select test type", "Blood Test", "Urine Test", "X-Ray", "MRI")
        val testTypeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, testTypes)
        testTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTestType.adapter = testTypeAdapter

        // Update price based on selected test type
        spinnerTestType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTestType = testTypes[position]
                val price = when (selectedTestType) {
                    "Blood Test" -> "Rs.700"
                    "Urine Test" -> "Rs.1400"
                    "X-Ray" -> "Rs.3000"
                    "MRI" -> "Rs.5000"
                    else -> "Select a test type"
                }
                editTextPrice.setText(price)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                editTextPrice.setText("Select a test type")
            }
        }

        // Next Button Logic: Pass data to LabTestDetailsActivity
        buttonNext.setOnClickListener {
            val selectedLocation = spinnerLocation.selectedItem.toString()
            val selectedTestType = spinnerTestType.selectedItem.toString()
            val price = editTextPrice.text.toString()

            // Validate selections before proceeding
            if (selectedLocation == "Select location" || selectedTestType == "Select test type" || price == "Select a test type") {
                android.widget.Toast.makeText(this, "Please select a location and test type", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, LabTestDetailsActivity::class.java).apply {
                putExtra("LOCATION", selectedLocation)
                putExtra("TEST_TYPE", selectedTestType)
                putExtra("PRICE", price)
            }
            startActivity(intent)
        }

        // Back Button Logic
        buttonBack.setOnClickListener {
            finish() // Close this activity and return to HomeActivity
        }
    }
}