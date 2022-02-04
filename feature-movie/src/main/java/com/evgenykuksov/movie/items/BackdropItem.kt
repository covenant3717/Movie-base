package com.evgenykuksov.movie.items

import android.annotation.SuppressLint
import android.view.MotionEvent
import coil.ImageLoader
import coil.load
import com.evgenykuksov.core.anim.startAnimationScale
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

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgBackdrop.apply {
                load(backdropPath, imageLoader)
                setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> viewHolder.containerView.startAnimationScale(0.95f) {}
                        MotionEvent.ACTION_UP -> viewHolder.containerView.startAnimationScale(1f) { onClick(backdropPath) }
                        MotionEvent.ACTION_CANCEL -> viewHolder.containerView.startAnimationScale(1f) {}
                        else -> {}
                    }
                    false
                }
            }
        }
    }
}