package com.jwitwit.navara.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jwitwit.navara.R
import com.jwitwit.navara.models.GroceryItem
import kotlinx.android.synthetic.main.grocery_item.view.*

class GroceryAdapter(private val groceryList: MutableList<GroceryItem>): RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    inner class GroceryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        return GroceryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.grocery_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val currentItem = groceryList[position]
        holder.itemView.tvItem.text = currentItem.name
    }

}