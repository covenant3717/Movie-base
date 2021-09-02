package com.evgenykuksov.moviebase.screens.movie.items

import android.annotation.SuppressLint
import android.view.MotionEvent
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.domain.movies.model.Actor
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.screens.overview.anim.startAnimationScale
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.movie_item_actor.view.*

internal data class ActorItem(
    private val actor: Actor,
    private val defaultImageLoader: ImageLoader,
    private val onClick: (actorId: Long) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.movie_item_actor

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgActor.load(actor.profilePath, defaultImageLoader) {
                transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_16)))
            }
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> v.startAnimationScale(0.9f) {}
                    MotionEvent.ACTION_UP -> v.startAnimationScale(1f) { onClick(actor.id) }
                    MotionEvent.ACTION_CANCEL -> v.startAnimationScale(1f) {}
                    else -> {
                    }
                }
                false
            }
        }
    }
}