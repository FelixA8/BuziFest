package com.example.buzifest.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buzifest.MainActivity
import com.example.buzifest.R
import com.example.buzifest.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.settingsLogOut.setOnClickListener {
            val sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            val editor = sharedpreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.settingsBackIV.setOnClickListener {
            finish()
        }
    }
}