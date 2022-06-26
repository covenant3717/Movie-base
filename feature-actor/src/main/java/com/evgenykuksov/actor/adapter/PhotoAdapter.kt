package com.evgenykuksov.actor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import com.evgenykuksov.actor.R
import com.evgenykuksov.core.anim.startAnimationAlpha
import kotlinx.android.synthetic.main.item_page_photo.view.*

internal class PhotoAdapter(
    private val emptyImageLoader: ImageLoader,
    private val onInfoClick: () -> Unit = {}
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<String?> = emptyList()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
        when {
            items[position]?.isNotBlank() == true -> TYPE_PHOTO
            items[position] == null -> TYPE_LOADING
            else -> throw IllegalArgumentException("Unknown type")
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_PHOTO -> PhotoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_page_photo, parent, false)
            )
            TYPE_LOADING -> LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_page_loading, parent, false)
            )
            else -> throw IllegalArgumentException("Unknown type")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PhotoViewHolder -> items[position]?.let { holder.bind(it) }
            is LoadingViewHolder -> items[position]?.let { holder.bind(it) }
            else -> {}
        }
    }

    fun setData(newItems: List<String?>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) {
            itemView.run {
                imgPhoto.load(item, emptyImageLoader) {
                    crossfade(true)
                }
                imgInfo.apply {
                    if (adapterPosition == 0) startAnimationAlpha(0f, 1f)
                    else isVisible = false
                    setOnClickListener { onInfoClick() }
                }
            }
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) {
            // nothing
        }
    }

    companion object {

        const val TYPE_PHOTO = 1
        const val TYPE_LOADING = 2
    }
}

