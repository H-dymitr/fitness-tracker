package com.example.fitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val introLogo = findViewById<ImageView>(R.id.intro_icon)

        introLogo.alpha = 0f
        introLogo.animate().setDuration(1500).alpha(1f).withEndAction{
            val nextActivity = if (this.isUserLoggedIn()) MainActivity::class.java else RegistrationActivity::class.java
            val i = Intent(this, nextActivity)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        // check SharedPreferences for user data
        val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
        val name = sharedPref.getString("name", null)
        return name != null
    }
}