package com.example.career_company

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportActionBar?.hide();
//        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        auth = FirebaseAuth.getInstance()

        // Delay for 3 seconds (3000 milliseconds)
        Handler(Looper.getMainLooper()).postDelayed({
            // Check if user is logged in
            val currentUser = auth.currentUser
            if (currentUser != null) {
                // User is logged in, navigate to HomePage
                val intent = Intent(this, Home_Page::class.java)
                startActivity(intent)
            } else {
                // User is not logged in, navigate to RegistrationPage
                val intent = Intent(this, Login_Page::class.java)
                startActivity(intent)
            }
            finish() // Finish splash screen activity
        }, 3000) // 3-second delay
    }
}