package com.miguel.cenoteapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miguel.cenoteapp.R
import com.miguel.cenoteapp.utils.Fomulas
import com.miguel.mapsboxexmaple.recyclerview.ItemsNavigation
import kotlinx.coroutines.processNextEventInCurrentThread

class AdapterRecyclerViewNavigation(
    private val itemsNavigation: List<ItemsNavigation> = ArrayList()
): RecyclerView.Adapter<AdapterRecyclerViewNavigation.ViewHolder>()  {
    val formulas = Fomulas()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_routes_indicate, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewDescription.text = itemsNavigation[position].description
        holder.textViewDistances.text = formulas.meterToKiloMeters(itemsNavigation[position].distances)
        holder.imageViewIcon.setImageResource(itemsNavigation[position].icon!!)
    }

    override fun getItemCount(): Int {
        return itemsNavigation.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageViewIcon: ImageView
        val textViewDescription: TextView
        val textViewDistances: TextView
        init {
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon)
            textViewDescription = itemView.findViewById(R.id.textViewDescription)
            textViewDistances = itemView.findViewById(R.id.textViewDistances)
        }
    }
}
