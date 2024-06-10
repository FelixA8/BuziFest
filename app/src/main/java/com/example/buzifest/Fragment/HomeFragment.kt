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
import com.example.buzifest.Activity.Home
import com.example.buzifest.Activity.NewsActivity
import com.example.buzifest.Activity.PortfolioDetail
import com.example.buzifest.Activity.SettingsActivity
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
    private lateinit var refreshHome: SwipeRefreshLayout
    private lateinit var trending: LinearLayout
    private lateinit var newsMenu: LinearLayout
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
        val sharedPreferences = requireContext().getSharedPreferences(MainActivity.SHARED_PREFS, Context.MODE_PRIVATE)
        val email = sharedPreferences.getString(MainActivity.EMAIL_KEY, null)
        binding = FragmentHomeBinding.inflate(inflater)
        val sqliteDb = DatabaseHelper(requireContext())
        portfolioRecyclerView = binding.homePortfolioRecycler
        newsRecyclerView = binding.homeRecyclerViewNews
        portfolioValue = binding.homePortfolioValue
        portfolioEarnings = binding.homeEarnings
        refreshHome = binding.refreshHome
        newsRecyclerView = binding.homeRecyclerViewNews

        portfolioValue = binding.homePortfolioValue
        portfolioEarnings = binding.homeEarnings

        trending = binding.homeMenuTrending
        newsMenu = binding.homeMenuNews

        refreshHome.isEnabled = false
        newsRecyclerView.isNestedScrollingEnabled = false
        binding.svHome.setOnScrollChangeListener { v: View, _, scrollY, _, _ ->
            refreshHome.isEnabled = (scrollY == 0)
        }

        refreshHome.setOnRefreshListener {
            lifecycleScope.launch {
                sqliteDb.clearDatabase()
                getPortfoliosData(requireContext())
                getNewsData(requireContext()) //CallPortfolioData and save it to sqlite.
                val newsList = sqliteDb.selectAllNews()
                homeNewsAdapter = HomeNewsAdapter(newsList)
                newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                newsRecyclerView.adapter = homeNewsAdapter
                getAllUserPortfoliosData(requireContext())
                val portfolioList = getUserPortfoliosPortofolio(requireContext())
                portfolioAdapter = PortfolioAdapter(portfolioList, requireContext(), viewLifecycleOwner)
                portfolioRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                portfolioRecyclerView.adapter = portfolioAdapter
                val tempValue = getCurrentUserValueData(email!!)
                binding.homePortfolioValue.text = "Rp. ${formatNumber(tempValue.totalValue)}"
                binding.homeEarnings.text = "Rp. ${formatNumber(tempValue.totalEarning)}"
                refreshHome.isRefreshing = false
            }
        }

        trending.setOnClickListener{
            val intent = Intent(context, PortfolioDetail::class.java)
            startActivity(intent)
        }

        newsMenu.setOnClickListener {
            val intent = Intent(context, NewsActivity::class.java)
            startActivity(intent)
        }

        var portfolioList = sqliteDb.selectAllPortfolios()
        var newsList = sqliteDb.selectTopNews()
        val allUserPortfolioList = sqliteDb.selectAllUserPortfolios()
        val userPortfolioList = sqliteDb.selectUserPortfoliosPortofolio(email!!)
        var summaryValue = sqliteDb.selectUserSummaryValue(email)

        if(portfolioList.isEmpty()) {
            lifecycleScope.launch {
                getPortfoliosData(requireContext()) //CallPortfolioData and save it to sqlite.
            }
        }

        if(newsList.isEmpty()) {
            lifecycleScope.launch {
                getNewsData(requireContext()) //CallPortfolioData and save it to sqlite.
                newsList = sqliteDb.selectTopNews()
                homeNewsAdapter = HomeNewsAdapter(newsList)
                newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                newsRecyclerView.adapter = homeNewsAdapter
            }
        } else {
            homeNewsAdapter = HomeNewsAdapter(newsList)
            newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            newsRecyclerView.adapter = homeNewsAdapter
        }

        if(allUserPortfolioList.isEmpty()) {
            lifecycleScope.launch {
                getAllUserPortfoliosData(requireContext())
            }
        }

        if(userPortfolioList.isEmpty()) {
            lifecycleScope.launch {
                val currList = getUserPortfoliosPortofolio(requireContext())
                portfolioAdapter = PortfolioAdapter(currList, requireContext(), viewLifecycleOwner)
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
                summaryValue = getCurrentUserValueData(currentEmail)
                summaryValue = sqliteDb.selectUserSummaryValue(currentEmail)
                binding.homePortfolioValue.text = "Rp. ${formatNumber(summaryValue.totalValue)}"
                binding.homeEarnings.text = "Rp. ${formatNumber(summaryValue.totalEarning)}"
            }
        } else {
            binding.homePortfolioValue.text = "Rp. ${formatNumber(summaryValue.totalValue)}"
            binding.homeEarnings.text = "Rp. ${formatNumber(summaryValue.totalEarning)}"
        }

        binding.homeMenuSettings.setOnClickListener {
            val intent = Intent(context, SettingsActivity::class.java)
            startActivity(intent)
        }

        //Activate The Drawer
        binding.profileImageView.setOnClickListener {
            (activity as? Home)?.openDrawer()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}