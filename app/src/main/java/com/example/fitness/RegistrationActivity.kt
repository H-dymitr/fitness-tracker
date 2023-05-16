package com.example.fitness

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etLastname: EditText
    private lateinit var etAge: EditText
    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar?.hide()

        etName = findViewById(R.id.etName)
        etLastname = findViewById(R.id.etLastname)
        etAge = findViewById(R.id.etAge)
        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        btnRegister = findViewById(R.id.btnSave)

        btnRegister.setOnClickListener {
            // Validate input (e.g., check for empty fields)
            if (!validateInput()) {
                return@setOnClickListener
            }

            // Save user data to shared preferences
            val sharedPref: SharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("name", etName.text.toString())
            editor.putString("lastname", etLastname.text.toString())
            editor.putInt("age", etAge.text.toString().toInt())
            editor.putInt("weight", etWeight.text.toString().toInt())
            editor.putInt("height", etHeight.text.toString().toInt())
            editor.apply()

            // Once registration is successful, navigate to the next activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    // isLoggedin = false
    private fun validateInput(): Boolean {
        var isValid = true

        if (etName.text.toString().isEmpty()) {
            etName.error = "Please enter your name"
            etName.requestFocus()
            isValid = false
        }

        if (etLastname.text.toString().isEmpty()) {
            etLastname.error = "Please enter your lastname"
            etLastname.requestFocus()
            isValid = false
        }

        if (etAge.text.toString().isEmpty()) {
            etAge.error = "Please enter your age"
            etAge.requestFocus()
            isValid = false
        }

        if (etWeight.text.toString().isEmpty()) {
            etWeight.error = "Please enter your weight"
            etWeight.requestFocus()
            isValid = false
        }

        if (etHeight.text.toString().isEmpty()) {
            etHeight.error = "Please enter your height"
            etHeight.requestFocus()
            isValid = false
        }

        return isValid
    }
}
