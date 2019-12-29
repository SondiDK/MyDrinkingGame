package com.osola.draganddrink.Dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.osola.draganddrink.R
import kotlinx.android.synthetic.main.card_list_item.view.*


class InfoDialogAdapter(val items : ArrayList<DataWrapper>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.card_list_item,
                parent,
                false
            )
        )
    }

    // Binds each  to aview
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCardText.text = items[position].name
        holder.pic.setImageResource(items[position].image)
        holder.tvCardDescription?.text = items[position].description
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvCardText: TextView = view.tv_card_title
    val pic = view.card_list_image
    val tvCardDescription = view.tv_card_descrip
}


data class DataWrapper(val name: String, val image: Int, val description: String) {
}