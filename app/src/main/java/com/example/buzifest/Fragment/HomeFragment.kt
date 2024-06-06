package com.example.buzifest.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.buzifest.Adapter.HomeNewsAdapter
import com.example.buzifest.Adapter.PortfolioAdapter
import com.example.buzifest.Data.*
import com.example.buzifest.Helper.*
import com.example.buzifest.MainActivity
import com.example.buzifest.R
import com.example.buzifest.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var portfolioRecyclerView: RecyclerView
    private lateinit var portfolioAdapter: PortfolioAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var portfolioValue: TextView
    private lateinit var portfolioEarnings: TextView
    private lateinit var sqliteDb: DatabaseHelper
    private lateinit var refreshHome: SwipeRefreshLayout

    // news recyler
    private lateinit var newsRecyclerView:RecyclerView
    private lateinit var homeNewsAdapter: HomeNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        sqliteDb = DatabaseHelper(requireContext())

        binding = FragmentHomeBinding.inflate(inflater)

        portfolioRecyclerView = binding.homePortfolioRecycler
        newsRecyclerView = binding.homeRecyclerViewNews
        portfolioValue = binding.homePortfolioValue
        portfolioEarnings = binding.homeEarnings
        refreshHome = binding.refreshHome


        newsRecyclerView = binding.homeRecyclerViewNews

        portfolioValue = binding.homePortfolioValue
        portfolioEarnings = binding.homeEarnings

        refreshHome.isEnabled = false

        binding.svHome.setOnScrollChangeListener { v: View, _, scrollY, _, _ ->
            refreshHome.isEnabled = false
            if(scrollY == 0) {
                refreshHome.isEnabled = true
                lifecycleScope.launch {
                    sqliteDb.clearDatabase()
                    getPortfoliosData(requireContext())
                    getNewsData(requireContext()) //CallPortfolioData and save it to sqlite.
                    val newsList = sqliteDb.selectAllNews()
                    homeNewsAdapter = HomeNewsAdapter(newsList)
                    newsRecyclerView.layoutManager = LinearLayoutManager(context)
                    newsRecyclerView.adapter = homeNewsAdapter
                    getAllUserPortfoliosData(requireContext())
                    val portfolioList = getUserPortfoliosPortofolio(currentEmail)
                    portfolioAdapter = PortfolioAdapter(portfolioList, requireContext(), viewLifecycleOwner)
                    portfolioRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    portfolioRecyclerView.adapter = portfolioAdapter
                    val tempValue = getCurrentUserValueData(currentEmail)
                    binding.homePortfolioValue.text = "Rp. ${formatNumber(tempValue.totalValue)}"
                    binding.homeEarnings.text = "Rp. ${formatNumber(tempValue.totalEarning)}"
                    refreshHome.isRefreshing = false
                }
            }
        }

        val portfolioList = sqliteDb.selectAllPortfolios()
        var newsList = sqliteDb.selectAllNews()
        val allUserPortfolioList = sqliteDb.selectAllUserPortfolios()
        val userPortfolioList = sqliteDb.selectUserPortfoliosPortofolio()
        val summaryValue = sqliteDb.selectUserSummaryValue(currentEmail)

        if(portfolioList.isEmpty()) {
            lifecycleScope.launch {
                getPortfoliosData(requireContext()) //CallPortfolioData and save it to sqlite.
            }
        }

        if(newsList.isEmpty()) {
            lifecycleScope.launch {
                getNewsData(requireContext()) //CallPortfolioData and save it to sqlite.
                newsList = sqliteDb.selectAllNews()
                homeNewsAdapter = HomeNewsAdapter(newsList)
                newsRecyclerView.layoutManager = LinearLayoutManager(context)
                newsRecyclerView.adapter = homeNewsAdapter
            }
        } else {
            homeNewsAdapter = HomeNewsAdapter(newsList)
            newsRecyclerView.layoutManager = LinearLayoutManager(context)
            newsRecyclerView.adapter = homeNewsAdapter
        }

        if(allUserPortfolioList.isEmpty()) {
            lifecycleScope.launch {
                getAllUserPortfoliosData(requireContext())
            }
        }

        if(userPortfolioList.isEmpty()) {
            lifecycleScope.launch {
                val portfolioList = getUserPortfoliosPortofolio(currentEmail)
                portfolioAdapter = PortfolioAdapter(portfolioList, requireContext(), viewLifecycleOwner)
                portfolioRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                portfolioRecyclerView.adapter = portfolioAdapter
            }
        } else {
            portfolioAdapter = PortfolioAdapter(userPortfolioList, requireContext(), viewLifecycleOwner)
            portfolioRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            portfolioRecyclerView.adapter = portfolioAdapter
        }

        if(summaryValue.totalValue == 0 && summaryValue.totalEarning == 0) {
            lifecycleScope.launch {
                val tempValue = getCurrentUserValueData(currentEmail)
                binding.homePortfolioValue.text = "Rp. ${formatNumber(tempValue.totalValue)}"
                binding.homeEarnings.text = "Rp. ${formatNumber(tempValue.totalEarning)}"
            }
        } else {
            binding.homePortfolioValue.text = "Rp. ${formatNumber(summaryValue.totalValue)}"
            binding.homeEarnings.text = "Rp. ${formatNumber(summaryValue.totalEarning)}"
        }
//        println("userPortfolioList = ${userPortfolioList}")




        binding.homeMenuSettings.setOnClickListener {
            val sharedpreferences = requireContext().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            val editor = sharedpreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}