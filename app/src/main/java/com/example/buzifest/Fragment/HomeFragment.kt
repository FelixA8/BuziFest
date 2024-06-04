package com.example.buzifest.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buzifest.Adapter.PortfolioAdapter
import com.example.buzifest.Data.News
import com.example.buzifest.Data.Portfolio
import com.example.buzifest.Helper.*
import com.example.buzifest.MainActivity
import com.example.buzifest.R
import com.example.buzifest.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var portfolioRecyclerView: RecyclerView
    private lateinit var portfolioAdapter: PortfolioAdapter
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater)

        portfolioRecyclerView = binding.homePortfolioRecycler

        binding.homeMenuSettings.setOnClickListener {
            val sharedpreferences = requireContext().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
            val editor = sharedpreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)

        }
        //CALL DATA FROM FIRESTORE
        lifecycleScope.launch {
            val portfolioList = getPortfoliosData() //CallPortfolioData
            val userData = getUserFromFirestoreByEmail(currentEmail) //CallUserData
            val userPortfolioList = getAllUserPortfoliosData() //Call User Portfolio List
            val newsList = getNewsData() //Call User List
            println(portfolioList)
            portfolioAdapter = PortfolioAdapter(portfolioList, viewLifecycleOwner)
            portfolioRecyclerView.layoutManager = LinearLayoutManager(context)
            portfolioRecyclerView.adapter = portfolioAdapter
//            val dummy =DUMMY_USERPORTFOLIODATA[0]
//            dummy.portfolioID = DUMMY_PORTFOLIODATA[0].id
//            changeUserAsset(dummy.purchaseAmount+dummy.totalProfit+userData!!, userData.email)
//            addUserPortfolio(dummy)

//            for (portfolio in portfolioList) {
//                val amount = getAllPurchaseAmountOfPortfolio(portfolioID = portfolio.id).totalInvested //Call the amount of invested portfolio
//                val totalInvestor = getAllPurchaseAmountOfPortfolio(portfolioID = portfolio.id).totalInvestor //Call the amount of total investor in a portfolio
//
//            }
            println(userPortfolioList)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}