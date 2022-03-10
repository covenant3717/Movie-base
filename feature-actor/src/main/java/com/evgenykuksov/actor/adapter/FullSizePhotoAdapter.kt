package com.evgenykuksov.actor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.evgenykuksov.actor.R
import kotlinx.android.synthetic.main.item_page.view.*

class FullSizePhotoAdapter(
    private val emptyImageLoader: ImageLoader,
    private var items: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int): Unit =
        holder.itemView.run {
            imgPhoto.load(items[position], emptyImageLoader)
        }

    fun setData(newItems: List<String>) {
        items = newItems
        notifyDataSetChanged()
    }

    companion object {

        private class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}

