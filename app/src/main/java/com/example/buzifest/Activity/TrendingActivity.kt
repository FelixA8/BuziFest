package com.example.buzifest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buzifest.Adapter.PortofolioPageAdapter
import com.example.buzifest.Adapter.TrendingAdapter
import com.example.buzifest.Helper.DatabaseHelper
import com.example.buzifest.Helper.currentEmail
import com.example.buzifest.R
import com.example.buzifest.databinding.ActivityTrendingBinding

class TrendingActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityTrendingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sqliteDb = DatabaseHelper(this)

        val portfolioList = sqliteDb.selectTrendingPortfolio()
        println("portfolioList: ${portfolioList}")
        val portfolioListAdapter = TrendingAdapter(portfolioList, this)
        binding.trendingRecycler.layoutManager = LinearLayoutManager(this)
        binding.trendingRecycler.adapter = portfolioListAdapter

        backButton= binding.detailBackButton

        backButton.setOnClickListener {
            finish()
        }


        binding.trendingRecycler
    }
}