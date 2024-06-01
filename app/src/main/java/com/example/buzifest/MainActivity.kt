package com.example.buzifest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.buzifest.Activity.Register
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var login_username: EditText
    private lateinit var login_password: EditText
    private lateinit var login_button: Button
    private lateinit var login_toRegister: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_username = findViewById(R.id.login_etUsername)
        login_password = findViewById(R.id.login_etPassword)
        login_button = findViewById(R.id.login_loginButton)
        login_toRegister = findViewById(R.id.login_tvToRegister)

        login_toRegister.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }


    }
}