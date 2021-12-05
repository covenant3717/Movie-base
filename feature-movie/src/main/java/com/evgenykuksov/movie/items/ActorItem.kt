package com.evgenykuksov.movie.items

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.core.anim.startAnimationScale
import com.evgenykuksov.domain.movies.model.Actor
import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_actor.view.*

internal data class ActorItem(
    private val actor: Actor,
    private val defaultImageLoader: ImageLoader,
    private val onClick: (actor: Actor, extras: FragmentNavigator.Extras) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.item_actor

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgActor.apply {
                transitionName = actor.profilePath
                load(actor.profilePath, defaultImageLoader) {
                    transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_16)))
                }
            }
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> v.startAnimationScale(0.9f) {}
                    MotionEvent.ACTION_UP -> v.startAnimationScale(1f) {
                        onClick(
                            actor,
                            FragmentNavigatorExtras(imgActor to actor.profilePath)
                        )
                    }
                    MotionEvent.ACTION_CANCEL -> v.startAnimationScale(1f) {}
                    else -> {}
                }
                false
            }
        }
    }
}