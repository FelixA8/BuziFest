package com.example.buzifest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buzifest.Data.Portfolio
import com.example.buzifest.R

class UpcomingAdapter(private val upcomingList: List<Portfolio>):RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val upcomingImage = itemView.findViewById<ImageView>(R.id.upcoming_recycler_image)
        val upcomingDays = itemView.findViewById<TextView>(R.id.upcoming_recycler_days)
        val upcomingLogo = itemView.findViewById<ImageView>(R.id.upcoming_recycler_logo)
        val upcomingName = itemView.findViewById<TextView>(R.id.upcoming_recyler_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.upcoming_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}