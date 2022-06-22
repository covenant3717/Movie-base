package com.evgenykuksov.movie.items

import coil.ImageLoader
import coil.load
import com.evgenykuksov.core.anim.setAnimScaleClickListener
import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_backdrop.view.*

internal data class BackdropItem(
    private val backdropPath: String,
    private val imageLoader: ImageLoader,
    private val onClick: (backdropPath: String) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.item_backdrop

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgBackdrop.apply {
                load(backdropPath, imageLoader)
                setAnimScaleClickListener { onClick(backdropPath) }
            }
        }
    }
}