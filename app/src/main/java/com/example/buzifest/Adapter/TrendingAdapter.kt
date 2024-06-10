package com.example.buzifest.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buzifest.Data.Portfolio
import com.example.buzifest.Data.TrendingPortfolio
import com.example.buzifest.Helper.formatNumber
import com.example.buzifest.R
import org.w3c.dom.Text


//================================== ini list nya gatau apa, default gua kasih portfolio ================================
class TrendingAdapter(private val trendingList: List<TrendingPortfolio>, context:Context):RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val trendingPoint =itemView.findViewById<ImageView>(R.id.trending_recyler_point)
        val totalInvested = itemView.findViewById<TextView>(R.id.trending_recyler_totalInvested)
        val trendingLogo = itemView.findViewById<ImageView>(R.id.trending_recyler_logo)
        val trendingName = itemView.findViewById<TextView>(R.id.trending_recycler_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.trending_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = trendingList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.portfolio.logo)
            .into(holder.trendingLogo)
        holder.trendingName.text = currentItem.portfolio.storeName
        holder.totalInvested.text = formatNumber(currentItem.totalInvested)
    }
}