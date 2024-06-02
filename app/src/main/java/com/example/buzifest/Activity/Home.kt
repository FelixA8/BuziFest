package com.example.buzifest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.buzifest.Data.User
import com.example.buzifest.Fragment.ChatFragment
import com.example.buzifest.Fragment.HomeFragment
import com.example.buzifest.Fragment.PortofolioFragment
import com.example.buzifest.Fragment.ShopListFragment
import com.example.buzifest.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class Home : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    lateinit var loggedUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val email = intent.getStringExtra("email")
        loggedUser = getUserFromFirestoreByEmail(email!!)

        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottomNav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.bottonNav_portfolio -> {
                    loadFragment(PortofolioFragment())
                    true
                }
                R.id.bottomNav_shopList -> {
                    loadFragment(ShopListFragment())
                    true
                }
                R.id.bottonNav_chat -> {
                    loadFragment(ChatFragment())
                    true
                }
                else -> {false}
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

    private fun getUserFromFirestoreByEmail(email: String): User {
        val db = FirebaseFirestore.getInstance()

        // Reference to the document with the given email as ID
        val docRef = db.collection("users").document(email)
        lateinit var fetchedUser: User
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Document exists, retrieve data
                    val userData = document.data
                    if (userData != null) {
                        // Process the retrieved data
                        val username = userData["username"] as? String
                        val email = userData["email"] as? String
                        val phoneNumber = userData["phoneNumber"] as? String
                        val firstName = userData["firstName"] as? String
                        val lastName = userData["lastName"] as? String
                        val idCardNumber = userData["idCardNumber"] as? String
                        val address = userData["address"] as? String
                        val balance = userData["balance"] as? Int
                        val asset = userData["asset"] as? Int
                        // Print or use the retrieved data
                        fetchedUser = User(username!!, firstName!!, lastName!!, email!!, idCardNumber!!, phoneNumber!!, balance!!, asset!!, address!!)
                        println("User Data: $userData")
                    }
                } else {
                    // Document does not exist
                    println("No such document!")
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
                println("Error getting document: $exception")
            }
        return fetchedUser
    }
}