package com.example.buzifest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buzifest.Data.Portfolio
import com.example.buzifest.Data.UserPortfolio
import com.example.buzifest.R
import org.w3c.dom.Text

class PortofolioPageAdapter(private val userPortofolioList: List<UserPortfolio>): RecyclerView.Adapter<PortofolioPageAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val portofolioPoint = itemView.findViewById<ImageView>(R.id.portofolio_recyler_point)
        val portofolioPercentage = itemView.findViewById<TextView>(R.id.portofolio_recyler_percentage)
        val portfolioEarnings = itemView.findViewById<TextView>(R.id.portofolio_recyler_earnings)
        val portofolioLogo = itemView.findViewById<ImageView>(R.id.portofolio_recyler_logo)
        val portofolioName = itemView.findViewById<TextView>(R.id.portofolio_recycler_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.portofolio_custom_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userPortofolioList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userPortofolioList[position]
        holder.portfolioEarnings.text = "0"
    }
}