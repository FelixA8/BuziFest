package com.example.buzifest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buzifest.Adapter.TrendingAdapter
import com.example.buzifest.Adapter.UpcomingAdapter
import com.example.buzifest.Helper.DatabaseHelper
import com.example.buzifest.R
import com.example.buzifest.databinding.ActivityUpcomingBinding

class UpcomingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityUpcomingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val sqliteDb = DatabaseHelper(this)
        val upcomingPortfolioList = sqliteDb.selectAllUpcomingPortfolio()
        println("upcoming: ${upcomingPortfolioList}")
        val upcomingPortfolioListAdapter = UpcomingAdapter(upcomingPortfolioList)
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.upcomingRecycler.layoutManager = gridLayoutManager
        binding.upcomingRecycler.adapter = upcomingPortfolioListAdapter
        binding.detailBackButton.setOnClickListener {
            finish()
        }
    }
}