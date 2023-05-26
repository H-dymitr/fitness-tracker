package com.example.fitness

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etLastname: EditText
    private lateinit var etAge: EditText
    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var tvHeader: TextView
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

        val preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val isUserRegistered = preferences.contains("name")

        if (isUserRegistered) {
            tvHeader = findViewById(R.id.tvHeader)
            tvHeader.text = getString(R.string.edit_profile)
            val name = preferences.getString("name", "")
            val lastname = preferences.getString("lastname", "")
            val age = preferences.getInt("age", 0);
            val weight = preferences.getInt("weight", 0)
            val height = preferences.getInt("height", 0)


            etName.setText(name)
            etLastname.setText(lastname)
            if (age != 0) {
                etAge.setText(age.toString())
            }
            if (weight != 0) {
                etWeight.setText(weight.toString())
            }
            if (height != 0) {
                etHeight.setText(height.toString())
            }
        }


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

            if (isUserRegistered) {
                // If user is already registered, return to the profile fragment
                finish()
            } else {
                // Once registration is successful, navigate to the next activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
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
