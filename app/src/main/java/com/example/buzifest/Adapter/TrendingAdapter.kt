package com.example.buzifest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buzifest.Data.Portfolio
import com.example.buzifest.R
import org.w3c.dom.Text


//================================== ini list nya gatau apa, default gua kasih portfolio ================================
class TrendingAdapter(private val trendingList: List<Portfolio>):RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val trendingPoint =itemView.findViewById<ImageView>(R.id.trending_recyler_point)
        val trendingPercentage = itemView.findViewById<TextView>(R.id.trending_recyler_percentage)
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
        TODO("Not yet implemented")
    }
}