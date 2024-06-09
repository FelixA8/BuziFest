package com.example.buzifest.Activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buzifest.Adapter.HomeNewsAdapter
import com.example.buzifest.Adapter.NewsAdapter
import com.example.buzifest.Helper.DatabaseHelper
import com.example.buzifest.R
import com.example.buzifest.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        val sqliteDb = DatabaseHelper(this)
        val newsList = sqliteDb.selectAllNews()
        val newsAdapter = NewsAdapter(newsList)
        binding.newsRecycler.layoutManager = LinearLayoutManager(this)
        binding.newsRecycler.adapter = newsAdapter

        binding.newsBackButton.setOnClickListener {
            finish()
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}