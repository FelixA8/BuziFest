package com.example.buzifest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buzifest.Data.News
import com.example.buzifest.R

class NewsAdapter(private var NewsList: List<News>):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val newsImage =itemView.findViewById<TextView>(R.id.news_recycler_image)
        val newsTitle = itemView.findViewById<TextView>(R.id.news_recycler_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_custom_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return NewsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}