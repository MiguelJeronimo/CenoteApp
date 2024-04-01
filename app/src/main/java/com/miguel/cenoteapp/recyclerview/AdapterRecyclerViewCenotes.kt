package com.miguel.mapsboxexmaple.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.carousel.MaskableFrameLayout
import com.miguel.cenoteapp.R
import com.miguel.mapsboxexmaple.models.Cenotes

class AdapterRecyclerViewCenotes(
    private val items_cenotes: List<ItemsRecyclerView> = ArrayList()
): RecyclerView.Adapter<AdapterRecyclerViewCenotes.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_image_cenotes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items_cenotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = items_cenotes[position].img
        println(url)
        Glide.with(holder.imageCenotes.context)
            .load(url)
            .into(holder.imageCenotes)
//        (holder.itemView as MaskableFrameLayout).setOnMaskChangedListener {
//                maskRect ->
//            // Any custom motion to run when mask size changes
//            holder.imageCenotes.setTranslationX(maskRect.left)
//            holder.imageCenotes.setAlpha(lerp(1F, 0F, 0F, 80F, maskRect.left))
//        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageCenotes: ImageView
        init {
            imageCenotes = itemView.findViewById(R.id.image_cenote)
        }
    }

}