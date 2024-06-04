package com.example.buzifest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.buzifest.Data.Portfolio
import com.example.buzifest.Helper.*
import com.example.buzifest.R
import kotlinx.coroutines.launch

class PortfolioAdapter(private val portfolioList: Array<Portfolio>,
private val lifecycleOwner: LifecycleOwner): RecyclerView.Adapter<PortfolioAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val portofolioLogo = itemView.findViewById<ImageView>(R.id.home_custom_horizontal_logo)
        val portfolioName = itemView.findViewById<TextView>(R.id.home_custom_horizontal_name)
        val portfolioPercentage = itemView.findViewById<TextView>(R.id.home_custom_horizontal_percentage)
        val portfolioRemaining = itemView.findViewById<TextView>(R.id.home_custom_horizontal_remaining)
        val portfolioGrossProfit = itemView.findViewById<TextView>(R.id.home_custom_horizontal_gross_profit)
        val portfolioSharesReleased = itemView.findViewById<TextView>(R.id.home_custom_horizontal_shares_released)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_custom_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return portfolioList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = portfolioList[position]

        lifecycleOwner.lifecycleScope.launch {
            val amount = getAllPurchaseAmountOfPortfolio(portfolioID = currentItem.id).totalInvested //Call the amount of invested portfolio
            val totalInvestor = getAllPurchaseAmountOfPortfolio(portfolioID = currentItem.id).totalInvestor //Call the amount of total investor in a portfolio

            holder.portfolioPercentage.text = ((amount/currentItem.fundingTarget)*100).toString() + "% gathered"
            println(amount)
            holder.portfolioRemaining.text = (currentItem.fundingTarget - amount).toString() + "remaining"
        }

        holder.portfolioName.text = currentItem.storeName
        holder.portfolioRemaining.text = currentItem.fundingTarget.toString()/// still not correct, needs current funding variable
        holder.portfolioGrossProfit.text =currentItem.grossProfit.toString()
        holder.portfolioSharesReleased.text = currentItem.publicShareStock.toString()
    }
}