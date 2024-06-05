package com.example.buzifest.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.buzifest.Data.Portfolio
import com.example.buzifest.Helper.formatNumber
import com.example.buzifest.R

class ShopAdapter(private val shopList: List<Portfolio>, private val context: Context):RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

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
        val currentItem = shopList[position]
        holder.shopBusinessValue.text = "Rp ${formatNumber(currentItem.grossProfit)}"
        holder.shopName.text = currentItem.storeName
        Glide.with(holder.itemView.context)
            .load(currentItem.logo)
            .into(holder.shopLogo)
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.shopImage)

    }
}