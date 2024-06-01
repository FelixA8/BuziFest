package com.example.buzifest.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.buzifest.R

class Register : AppCompatActivity() {

    private lateinit var register_username: EditText
    private lateinit var register_email: EditText
    private lateinit var register_password: EditText
    private lateinit var register_phoneNumber: EditText
    private lateinit var register_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_username = findViewById(R.id.register_etUsername)
        register_email = findViewById(R.id.register_etEmail)
        register_password = findViewById(R.id.register_etPassword)
        register_phoneNumber = findViewById(R.id.register_phoneNumber)
        register_button = findViewById(R.id.register_btnNext)


        register_button.setOnClickListener{
            val intent = Intent(this, Biodata::class.java)
            startActivity(intent)
        }
    }
}