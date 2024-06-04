package com.example.buzifest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.buzifest.Data.*
import com.example.buzifest.Fragment.ChatFragment
import com.example.buzifest.Fragment.HomeFragment
import com.example.buzifest.Fragment.PortofolioFragment
import com.example.buzifest.Fragment.ShopListFragment
import com.example.buzifest.Helper.addPortfolio
import com.example.buzifest.Helper.currentEmail
import com.example.buzifest.Helper.getPortfoliosData
import com.example.buzifest.Helper.getUserFromFirestoreByEmail
import com.example.buzifest.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch

class Home : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    lateinit var loggedUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val email = intent.getStringExtra("email")
        currentEmail = email!!
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
}