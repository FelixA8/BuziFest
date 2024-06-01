package com.example.buzifest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.buzifest.Activity.Home
import com.example.buzifest.Activity.Register
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var loginUsername: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var loginToRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginUsername = findViewById(R.id.login_etUsername)
        loginPassword = findViewById(R.id.login_etPassword)
        loginButton = findViewById(R.id.login_loginButton)
        loginToRegister = findViewById(R.id.login_tvToRegister)

        loginToRegister.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        loginButton.setOnClickListener {
            if(loginUsername.text.isEmpty() || loginPassword.text.isEmpty()) {
                Toast.makeText(this, "Please Enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(loginUsername.text.toString(), loginPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            print(user);
                            val intent = Intent(this, Home::class.java).apply {
                                putExtra("email", user!!.email)
                            }
                            startActivity(intent)
                            // Update UI or navigate to the main activity
                        } else {
                            Toast.makeText(this, "Wrong Email or Password       ", Toast.LENGTH_SHORT).show()
                            // If sign in fails, display a message to the user.
                            // Update UI
                        }
                    }
            }
        }
    }

}