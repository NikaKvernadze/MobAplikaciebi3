package com.example.emailpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var changePasswordEditText: EditText
    private lateinit var changePasswordButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        mAuth = FirebaseAuth.getInstance()

        changePasswordButton = findViewById(R.id.changePasswordButton)
        changePasswordEditText = findViewById(R.id.changePasswordEditText)

        changePasswordButton.setOnClickListener {
            val newPassword = changePasswordEditText.text.toString()
            if (newPassword.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_LONG).show()
            } else {
                mAuth.currentUser?.updatePassword(newPassword)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, PersonActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show()
                        }

                    }
            }
        }

    }
}