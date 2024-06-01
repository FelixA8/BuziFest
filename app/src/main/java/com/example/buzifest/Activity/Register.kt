package com.example.buzifest.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.buzifest.R

class Register : AppCompatActivity() {

    private lateinit var registerUsername: EditText
    private lateinit var registerEmail: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerPhoneNumber: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerUsername = findViewById(R.id.register_etUsername)
        registerEmail = findViewById(R.id.register_etEmail)
        registerPassword = findViewById(R.id.register_etPassword)
        registerPhoneNumber = findViewById(R.id.register_phoneNumber)
        registerButton = findViewById(R.id.register_btnNext)


        registerButton.setOnClickListener{
            if(registerUsername.text.isEmpty() || registerUsername.text.length <= 4){
                Toast.makeText(this, "Username must be more than 4 characters.", Toast.LENGTH_SHORT).show()
            } else if(registerEmail.text.isEmpty() || !registerEmail.text.contains(".com")){
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            } else if(registerPassword.text.isEmpty() || registerPassword.text.length < 8){
                Toast.makeText(this, "Password must be 8 characters or more.", Toast.LENGTH_SHORT).show()
            } else if(registerPhoneNumber.text.isEmpty() || registerPhoneNumber.text.length < 12) {
                Toast.makeText(this, "Please Input a valid Phone Number", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, Biodata::class.java).apply {
                    putExtra("username", registerUsername.text.toString())
                    putExtra("email", registerEmail.text.toString())
                    putExtra("password", registerPassword.text.toString())
                    putExtra("phonenumber", registerPhoneNumber.text.toString())
                }
                startActivity(intent)
            }
        }
    }
}