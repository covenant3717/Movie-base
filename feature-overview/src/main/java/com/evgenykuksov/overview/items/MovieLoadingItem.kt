package com.evgenykuksov.overview.items

import coil.ImageLoader
import coil.load
import com.evgenykuksov.overview.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_loading.view.*

internal data class MovieLoadingItem(private val gifLoader: ImageLoader) : Item() {

    override fun getLayout(): Int = R.layout.item_loading

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgLoading.load(R.drawable.gif_loading, gifLoader)
        }
    }
}