package com.evgenykuksov.moviebase.screens.overview.items

import coil.ImageLoader
import coil.load
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.overview_item_loading.view.*

internal data class MovieLoadingItem(private val gifLoader: ImageLoader) : Item() {

    override fun getLayout(): Int = R.layout.overview_item_loading

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgLoading.load(R.drawable.overview_gif_loading, gifLoader)
        }
    }
}