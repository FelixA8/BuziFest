package com.example.buzifest.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        newsRecyclerView = binding.homeRecyclerViewNews

        portfolioValue = binding.homePortfolioValue
        portfolioEarnings = binding.homeEarnings

        val portfolioList = sqliteDb.selectAllPortfolios()
        val newsList = sqliteDb.selectAllNews()
        val userPortfolioList = sqliteDb.selectAllUserPortfolios()
        println("dbPortfolio: ${portfolioList}")
        println("newsList: ${newsList}")
        println("userPortfolioList = ${userPortfolioList}")

        if(portfolioList.isEmpty()) {
            lifecycleScope.launch {
                val fetchedPortfolioList = getPortfoliosData(requireContext()) //CallPortfolioData and save it to sqlite.
                portfolioAdapter = PortfolioAdapter(fetchedPortfolioList, viewLifecycleOwner)
                portfolioRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                portfolioRecyclerView.adapter = portfolioAdapter
            }
        } else {
            portfolioAdapter = PortfolioAdapter(portfolioList, viewLifecycleOwner)
            portfolioRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            portfolioRecyclerView.adapter = portfolioAdapter
        }

        if(newsList.isEmpty()) {
            lifecycleScope.launch {
                val fetchedNewsList = getNewsData(requireContext()) //CallPortfolioData and save it to sqlite.
                homeNewsAdapter = HomeNewsAdapter(fetchedNewsList)
                newsRecyclerView.layoutManager = LinearLayoutManager(context)
                newsRecyclerView.adapter = homeNewsAdapter
            }
        } else {
            homeNewsAdapter = HomeNewsAdapter(newsList)
            newsRecyclerView.layoutManager = LinearLayoutManager(context)
            newsRecyclerView.adapter = homeNewsAdapter
        }

        if(userPortfolioList.isEmpty()) {
            lifecycleScope.launch {
                val fetchedNewsList = getAllUserPortfoliosData(requireContext())
                //TODO
            }
        } else {
            //TODO
        }

        sqliteDb.selectPurchaseAmountOfPortfolio("ab2bb03c-f8f4-4260-86ba-4ee4e39b1e5c")

        binding.homeMenuSettings.setOnClickListener {
            val sharedpreferences = requireContext().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            val editor = sharedpreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        lifecycleScope.launch {
//            val userPortfolioList = getAllUserPortfoliosData() //Call User Portfolio List
//            val currentUserData = getCurrentUserValueData(currentEmail) //Call User Value
//            val newsList = getNewsData(requireContext()) //Call news List
//            val userPortfolio = getAllCurrentUserPortfoliosData(currentEmail)
        }



        // Inflate the layout for this fragment
        return binding.root
    }
}