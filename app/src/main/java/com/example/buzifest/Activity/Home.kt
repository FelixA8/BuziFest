package com.example.buzifest.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch

class Home : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val sharedpreferences = getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)

        val tempUserName = sharedpreferences.getString(MainActivity.USERNAME_KEY, null)
        val email = sharedpreferences.getString(MainActivity.EMAIL_KEY, null)
        //Check if fetching user from firebase is required/ data is kept in sharedprefs.
        if(tempUserName == null) {
            lifecycleScope.launch {
                val editor = sharedpreferences.edit()
                val userData = getUserFromFirestoreByEmail(email!!) //CallUserData
                editor.putString(MainActivity.USERNAME_KEY, userData?.userName)
                editor.putString(MainActivity.FIRSTNAME_KEY, userData?.firstName)
                editor.putString(MainActivity.LASTNAME_KEY, userData?.lastName)
                editor.putString(MainActivity.IDCARDNUMBER_KEY, userData?.idCardNumber)
                editor.putString(MainActivity.PHONENUMBER_KEY, userData?.phoneNumber)
                editor.putString(MainActivity.BALANCE_KEY, userData?.balance.toString())
                editor.putString(MainActivity.ASSET_KEY, userData?.asset.toString())
                editor.putString(MainActivity.ADDRESS_KEY, userData?.address)
                editor.apply()
                val userName = sharedpreferences.getString(MainActivity.USERNAME_KEY, null)
                val firstName = sharedpreferences.getString(MainActivity.FIRSTNAME_KEY, null)
                val lastName = sharedpreferences.getString(MainActivity.LASTNAME_KEY, null)
                val idCardNumber = sharedpreferences.getString(MainActivity.IDCARDNUMBER_KEY, null)
                val phoneNumber = sharedpreferences.getString(MainActivity.PHONENUMBER_KEY, null)
                val balance = sharedpreferences.getString(MainActivity.BALANCE_KEY, null)
                val asset = sharedpreferences.getString(MainActivity.ASSET_KEY, null)
                val address = sharedpreferences.getString(MainActivity.ADDRESS_KEY, null)

                currentEmail = email
                currentUserName = userName!!
                currentFirstName = firstName!!
                currentLastName = lastName!!
                currentIdCardNumber = idCardNumber!!
                currentPhoneNumber = phoneNumber!!
                currentBalance = balance!!.toInt()
                currentAsset = asset!!.toInt()
                currentAddress = address!!
            }
        } else {
            val userName = sharedpreferences.getString(MainActivity.USERNAME_KEY, null)
            val firstName = sharedpreferences.getString(MainActivity.FIRSTNAME_KEY, null)
            val lastName = sharedpreferences.getString(MainActivity.LASTNAME_KEY, null)
            val idCardNumber = sharedpreferences.getString(MainActivity.IDCARDNUMBER_KEY, null)
            val phoneNumber = sharedpreferences.getString(MainActivity.PHONENUMBER_KEY, null)
            val balance = sharedpreferences.getString(MainActivity.BALANCE_KEY, null)
            val asset = sharedpreferences.getString(MainActivity.ASSET_KEY, null)
            val address = sharedpreferences.getString(MainActivity.ADDRESS_KEY, null)

            currentEmail = email!!
            currentUserName = userName!!
            currentFirstName = firstName!!
            currentLastName = lastName!!
            currentIdCardNumber = idCardNumber!!
            currentPhoneNumber = phoneNumber!!
            currentBalance = balance!!.toInt()
            currentAsset = asset!!.toInt()
            currentAddress = address!!
        }

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