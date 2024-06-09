package com.example.buzifest.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.buzifest.Data.*
import com.example.buzifest.Fragment.ChatFragment
import com.example.buzifest.Fragment.HomeFragment
import com.example.buzifest.Fragment.PortofolioFragment
import com.example.buzifest.Fragment.ShopListFragment
import com.example.buzifest.Helper.*
import com.example.buzifest.MainActivity
import com.example.buzifest.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import androidx.drawerlayout.widget.DrawerLayout

class Home : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        bottomNav = findViewById(R.id.bottomNav)

        val sharedpreferences =
            getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        val tempUserName = sharedpreferences.getString(MainActivity.USERNAME_KEY, null)
        val email = sharedpreferences.getString(MainActivity.EMAIL_KEY, null)

        if (tempUserName == null) {
            lifecycleScope.launch {
                val editor = sharedpreferences.edit()
                val userData = getUserFromFirestoreByEmail(email!!)
                editor.putString(MainActivity.USERNAME_KEY, userData?.userName)
                editor.putString(MainActivity.FIRSTNAME_KEY, userData?.firstName)
                editor.putString(MainActivity.LASTNAME_KEY, userData?.lastName)
                editor.putString(MainActivity.IDCARDNUMBER_KEY, userData?.idCardNumber)
                editor.putString(MainActivity.PHONENUMBER_KEY, userData?.phoneNumber)
                editor.putString(MainActivity.BALANCE_KEY, userData?.balance.toString())
                editor.putString(MainActivity.ASSET_KEY, userData?.asset.toString())
                editor.putString(MainActivity.ADDRESS_KEY, userData?.address)
                editor.apply()
                setUserDetails(sharedpreferences)
            }
        } else {
            setUserDetails(sharedpreferences)
        }
        // Set up the drawer toggle
        drawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.drawer_open, R.string.drawer_close
        )

        drawerLayout.addDrawerListener(drawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerToggle.syncState()

        val headerView = LayoutInflater.from(this).inflate(R.layout.custom_drawer_header, navigationView, false)
        navigationView.addHeaderView(headerView)

        val headerTV = headerView.findViewById<TextView>(R.id.drawer_header_username)
        headerTV.text = currentUserName

        setupBottomNavigation()
        setupDrawerNavigation()
        loadFragment(HomeFragment())
    }

    fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun setupBottomNavigation() {
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
                else -> false
            }
        }
    }

    private fun setupDrawerNavigation() {
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_about -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_seller -> {
                    loadFragment(PortofolioFragment())
                    true
                }
                R.id.nav_top_up -> {
                    val intent = Intent(this, TopUpActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(androidx.navigation.ui.R.anim.design_bottom_sheet_slide_in, androidx.navigation.ui.R.anim.design_bottom_sheet_slide_out)
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    private fun setUserDetails(sharedpreferences: SharedPreferences) {
        currentUserName = sharedpreferences.getString(MainActivity.USERNAME_KEY, null)!!
        currentFirstName = sharedpreferences.getString(MainActivity.FIRSTNAME_KEY, null)!!
        currentLastName = sharedpreferences.getString(MainActivity.LASTNAME_KEY, null)!!
        currentIdCardNumber = sharedpreferences.getString(MainActivity.IDCARDNUMBER_KEY, null)!!
        currentPhoneNumber = sharedpreferences.getString(MainActivity.PHONENUMBER_KEY, null)!!
        currentBalance = sharedpreferences.getString(MainActivity.BALANCE_KEY, null).toString().toInt()
        currentAsset = sharedpreferences.getString(MainActivity.ASSET_KEY, null).toString().toInt()
        currentAddress = sharedpreferences.getString(MainActivity.ADDRESS_KEY, null)!!
        currentEmail = sharedpreferences.getString(MainActivity.EMAIL_KEY, null)!!
        println("balance: ${currentBalance}")
    }
}

