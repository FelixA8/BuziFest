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
        if(email.isEmpty()) {
            return User("", "", "", "", "", "", 0,0,"")
        }

        lateinit var username:String
        lateinit var phoneNumber:String
        lateinit var firstName:String
        lateinit var lastName:String
        lateinit var idCardNumber:String
        lateinit var address:String
        lateinit var balance:String
        lateinit var asset:String
        println(email)
        val db = FirebaseFirestore.getInstance()

        // Reference to the document with the given email as ID
        val docRef = db.collection("users").document(email)
        var fetchedUser: User = User("", "", "", "", "", "", 0,0,"")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Document exists, retrieve data
                    val userData = document.data
                    if (userData != null) {
                        // Process the retrieved data
                        username = (userData["username"] as? String).toString()
                        phoneNumber = (userData["phoneNumber"] as? String).toString()
                        firstName = (userData["firstName"] as? String).toString()
                        lastName = (userData["lastName"] as? String).toString()
                        idCardNumber = (userData["idCardNumber"] as? String).toString()
                        address = (userData["address"] as? String).toString()
                        balance = (userData["balance"] as? Number).toString()
                        asset = (userData["asset"] as? Number).toString()
                        // Print or use the retrieved data
                        fetchedUser = User(username, firstName, lastName, email, idCardNumber, phoneNumber, balance.toInt(), asset.toInt(), address)
                        println("User Data: $fetchedUser")
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