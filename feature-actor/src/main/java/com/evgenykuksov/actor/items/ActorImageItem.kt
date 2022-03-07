package com.evgenykuksov.actor.items

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.annotation.DrawableRes
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.actor.R
import com.evgenykuksov.core.anim.startAnimationScale
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_actor_image.view.*

internal class ActorImageItem(
    private val imagePath: String? = null,
    @DrawableRes private val imagePathRes: Int? = null,
    private val imageLoader: ImageLoader,
    private val onClick: (image: String?) -> Unit = {}
) : Item() {

    override fun getLayout(): Int = R.layout.item_actor_image

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgActor.apply {
                imagePath?.let { load(it, imageLoader) { transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_10))) } }
                imagePathRes?.let { load(it, imageLoader) { transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_10))) } }
                setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> startAnimationScale(0.95f) {}
                        MotionEvent.ACTION_UP -> startAnimationScale(1f) { onClick(imagePath) }
                        MotionEvent.ACTION_CANCEL -> startAnimationScale(1f) {}
                        else -> {}
                    }
                    false
                }
            }
        }
    }
}