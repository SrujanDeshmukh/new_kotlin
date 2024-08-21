package com.example.career_company

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login_Page : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var tvRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize UI elements
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnSignIn = findViewById(R.id.btnSignIn)
        tvRegister = findViewById(R.id.tvRegister)

        btnSignIn.setOnClickListener {
            val email = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to Registration Page
        tvRegister.setOnClickListener {
            val intent = Intent(this, Registration_Page::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, navigate to Home Page
                    val intent = Intent(this, Home_Page::class.java)
                    startActivity(intent)
                    finish() // Finish login activity
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginPage", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed. ${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
