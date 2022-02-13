package com.evgenykuksov.movie.items

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
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
    private val onClick: (extras: FragmentNavigator.Extras) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.item_actor

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgActor.let {
                it.transitionName = actor.profilePath
                it.load(actor.profilePath, defaultImageLoader) {
                    transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_14)))
                }
                it.setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> startAnimationScale(0.95f) {}
                        MotionEvent.ACTION_UP -> startAnimationScale(1f) {
                            onClick(FragmentNavigatorExtras(imgActor to actor.profilePath))
                        }
                        MotionEvent.ACTION_CANCEL -> startAnimationScale(1f) {}
                        else -> {}
                    }
                    false
                }
            }
            tvCharacter.text = actor.character
        }
    }
}