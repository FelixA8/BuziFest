package com.example.buzifest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.buzifest.Data.Portfolio
import com.example.buzifest.R

class ShopAdapter(private val shopList: List<Portfolio>):RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val shopImage = itemView.findViewById<ImageView>(R.id.shop_recycler_image)
        val shopLogo = itemView.findViewById<ImageView>(R.id.shop_recycler_logo)
        val shopName = itemView.findViewById<TextView>(R.id.shop_recyler_name)
        val shopBusinessValue = itemView.findViewById<TextView>(R.id.shop_recycler_business_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shop_custom_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}