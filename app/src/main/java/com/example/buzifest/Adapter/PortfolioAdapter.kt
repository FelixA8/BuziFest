package com.example.buzifest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buzifest.Data.Portfolio
import com.example.buzifest.Helper.*
import com.example.buzifest.R
import kotlinx.coroutines.launch

class PortfolioAdapter(private val portfolioList: List<Portfolio>,
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
            try {
                // Fetch the portfolio amounts
                val portfolioData = getAllPurchaseAmountOfPortfolio(portfolioID = currentItem.id)

                val amount = portfolioData.totalInvested // Amount of invested portfolio
                val totalInvestor = portfolioData.totalInvestor // Amount of total investors in a portfolio

                // Debugging: Print the fetched data
                println("Fetched amount: $amount")
                println("Fetched fundingTarget: ${currentItem.fundingTarget}")
                println((amount.toDouble()/currentItem.fundingTarget.toDouble()))
                // Ensure currentItem.fundingTarget is not zero to avoid division by zero
                if (currentItem.fundingTarget != 0) {
                    val result = String.format("%.2f", (amount.toDouble() / currentItem.fundingTarget.toDouble()) * 100)
                    holder.portfolioPercentage.text = result + "% gathered"

                } else {
                    holder.portfolioPercentage.text = "Funding target is zero"
                }

                holder.portfolioRemaining.text = (currentItem.fundingTarget - amount).toString() + " remaining"
            } catch (e: Exception) {
                // Handle any exceptions that may occur during the coroutine execution
                println("Error fetching portfolio data: ${e.message}")
            }
        }

        Glide.with(holder.itemView.context)
            .load(currentItem.logo)
            .into(holder.portofolioLogo)

        holder.portfolioName.text = currentItem.storeName
        holder.portfolioRemaining.text = currentItem.fundingTarget.toString()/// still not correct, needs current funding variable
        holder.portfolioGrossProfit.text =currentItem.grossProfit.toString()
        holder.portfolioSharesReleased.text = currentItem.publicShareStock.toString()
    }
}