package com.example.buzifest.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buzifest.Data.News
import com.example.buzifest.R

class HomeNewsAdapter(private val newsHomeList: List<News>): RecyclerView.Adapter<HomeNewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val homeNewsImage = itemView.findViewById<ImageView>(R.id.home_recycler_news_photo)
        val homeNewsTitle = itemView.findViewById<TextView>(R.id.home_recyler_news_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_custom_news_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsHomeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsHomeList[position]

//        holder.homeNewsImage.setImageResource(currentItem.newsImageUrl)
        holder.homeNewsTitle.text = currentItem.newsTitle

        Glide.with(holder.itemView.context)
            .load(currentItem.newsImageUrl)
            .into(holder.homeNewsImage)

        holder.homeNewsImage.setOnClickListener{
            val newsURL = currentItem.newsLinkUrl
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsURL))
            holder.itemView.context.startActivity(intent)

        }


    }
}