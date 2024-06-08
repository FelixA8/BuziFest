package com.example.buzifest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.buzifest.Activity.Home
import com.example.buzifest.Activity.Register
import com.example.buzifest.Helper.DatabaseHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
        const val USERNAME_KEY = "username_key"
        const val FIRSTNAME_KEY = "firstname_key"
        const val LASTNAME_KEY = "lastname_key"
        const val IDCARDNUMBER_KEY = "idcardnumber_key"
        const val PHONENUMBER_KEY = "phonenumber_key"
        const val BALANCE_KEY = "balance_key"
        const val ASSET_KEY = "asset_key"
        const val ADDRESS_KEY = "address_key"
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var sqliteDb: DatabaseHelper
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var loginToRegister: TextView
    private lateinit var sharedpreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedpreferences = getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        sqliteDb = DatabaseHelper(this)
        sqliteDb.clearDatabase()
        val loggedEmail = sharedpreferences.getString(EMAIL_KEY, null)
        val loggedPassword = sharedpreferences.getString(PASSWORD_KEY, null)
        if(loggedEmail != null && loggedPassword != null) {
            val intent = Intent(this, Home::class.java).apply {
                putExtra("email", loggedEmail)
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        loginEmail = findViewById(R.id.login_etEmail)
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
            if(loginEmail.text.isEmpty() || loginPassword.text.isEmpty()) {
                Toast.makeText(this, "Please Enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                println("task")
                auth.signInWithEmailAndPassword(loginEmail.text.toString(), loginPassword.text.toString())
                    .addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            val intent = Intent(this, Home::class.java).apply {
                                putExtra("email", user!!.email.toString())
                                val editor = sharedpreferences.edit()
                                editor.clear()
                                editor.putString(EMAIL_KEY, loginEmail.text.toString())
                                editor.putString(PASSWORD_KEY, loginPassword.text.toString())
                                editor.apply()
                            }
                            startActivity(intent)
                            // Update UI or navigate to the main activity
                        } else {
                            Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_SHORT).show()
                            // If sign in fails, display a message to the user.
                            // Update UI
                        }
                    }
            }
        }
    }
}