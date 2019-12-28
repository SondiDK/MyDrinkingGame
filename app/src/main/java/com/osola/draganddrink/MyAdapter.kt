package com.osola.draganddrink

import android.content.ClipDescription
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_list_item.view.*


class MyAdapter(val items : ArrayList<DataWrapper>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCardText?.text = items[position].name
        holder.pic.setImageResource(items[position].image)
        holder.tvCardDescription?.text = items[position].description
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvCardText = view.tv_card_title
    val pic = view.card_list_image
    val tvCardDescription = view.tv_card_descrip
}


data class DataWrapper(val name: String, val image: Int, val description: String) {
}