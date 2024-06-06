package com.example.buzifest.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buzifest.Adapter.HomeNewsAdapter
import com.example.buzifest.Adapter.PortofolioPageAdapter
import com.example.buzifest.Helper.DatabaseHelper
import com.example.buzifest.Helper.currentBalance
import com.example.buzifest.Helper.currentEmail
import com.example.buzifest.Helper.formatNumber
import com.example.buzifest.R
import com.example.buzifest.databinding.FragmentPortofolioBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PortofolioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PortofolioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sqliteDb = DatabaseHelper(requireContext())
        val binding = FragmentPortofolioBinding.inflate(layoutInflater)
        val summaryValue = sqliteDb.selectUserSummaryValue(currentEmail)
        val balance = currentBalance
        val asset = summaryValue.totalValue + balance + summaryValue.totalEarning

        val userPortfolioList = sqliteDb.selectUserPortfolio(currentEmail)

        val portfolioListAdapter = PortofolioPageAdapter(userPortfolioList, requireContext())
        binding.portofolioRecycler.layoutManager = LinearLayoutManager(context)
        binding.portofolioRecycler.adapter = portfolioListAdapter

        binding.portofolioPortfolioValue.text = "Rp. " + formatNumber(summaryValue.totalValue)
        binding.portofolioEarnings.text = "Rp. " + formatNumber(summaryValue.totalEarning)
        binding.portofolioAsset.text = "Rp. " + formatNumber(asset)
        binding.portofolioBalance.text = "Rp. " + formatNumber(balance)

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PortofolioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PortofolioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}