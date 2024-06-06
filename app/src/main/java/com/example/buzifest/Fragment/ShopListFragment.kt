package com.example.buzifest.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buzifest.Adapter.PortofolioPageAdapter
import com.example.buzifest.Adapter.ShopAdapter
import com.example.buzifest.Helper.DatabaseHelper
import com.example.buzifest.R
import com.example.buzifest.databinding.FragmentShopListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShopListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShopListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShopListBinding.inflate(layoutInflater)
        val sqliteDb = DatabaseHelper(requireContext())
        val portfolioList = sqliteDb.selectAllPortfolios()
        val shopAdapter = ShopAdapter(portfolioList, requireContext())
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.shopRecycler.layoutManager = gridLayoutManager
        binding.shopRecycler.adapter = shopAdapter
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
         * @return A new instance of fragment ShopListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShopListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}