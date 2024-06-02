package com.example.buzifest.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.buzifest.MainActivity
import com.example.buzifest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Biodata : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var bioDataFirstName: EditText
    private lateinit var bioDataLastName: EditText
    private lateinit var bioDataAddress: EditText
    private lateinit var bioDataIdCardNumber:EditText
    private lateinit var bioDataButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biodata)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val email = intent.getStringExtra("email")
        val name = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val phone = intent.getStringExtra("phonenumber")

        bioDataFirstName = findViewById(R.id.biodata_etfirstName)
        bioDataLastName = findViewById(R.id.biodata_etlastName)
        bioDataAddress = findViewById(R.id.biodata_etaddress)
        bioDataIdCardNumber = findViewById(R.id.biodata_etidCardNumber)
        bioDataButton = findViewById(R.id.biodata_btnRegister)

        bioDataButton.setOnClickListener {
            if(bioDataFirstName.text.isEmpty()) {
                Toast.makeText(this, "Please Enter your first name", Toast.LENGTH_SHORT).show()
            } else if(bioDataLastName.text.isEmpty()) {
                Toast.makeText(this, "Please Enter your last name", Toast.LENGTH_SHORT).show()
            } else if(bioDataAddress.text.isEmpty()) {
                Toast.makeText(this, "Please Enter your address", Toast.LENGTH_SHORT).show()
            } else if(bioDataIdCardNumber.text.isEmpty()) {
                Toast.makeText(this, "Please Enter your id card", Toast.LENGTH_SHORT).show()
            } else {
                signUp(email!!, password!!)
                saveUserToFirestore(name!!, email, phone!!, bioDataFirstName.text.toString(), bioDataLastName.text.toString(), bioDataIdCardNumber.text.toString(), bioDataAddress.text.toString())
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveUserToFirestore(userName:String, email:String, phoneNumber:String, firstName:String,lastName:String, idCardNumber:String, address:String) {
        val user = hashMapOf(
            "username" to userName,
            "email" to email,
            "phoneNumber" to phoneNumber,
            "firstName" to firstName,
            "lastName" to lastName,
            "idCardNumber" to idCardNumber,
            "address" to address,
            "balance" to 0,
            "asset" to 0,
        )

        db.collection("users").document(email)
            .set(user)
            .addOnSuccessListener {
                // Successfully added user to Firestore
            }
            .addOnFailureListener { e ->
                // Failed to add user to Firestore
                print(e)
            }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    print("success")
                    val user = auth.currentUser
                    // Update UI or navigate to the main activity
                } else {
                    // If sign in fails, display a message to the user.
                    // Update UI
                }
            }
    }
}