package com.evgenykuksov.movie.items

import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.core.anim.setAnimScaleClickListener
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

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgActor.let {
                it.transitionName = actor.profilePath
                it.load(actor.profilePath, defaultImageLoader) {
                    transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_14)))
                }
                it.setAnimScaleClickListener {
                    onClick(FragmentNavigatorExtras(imgActor to actor.profilePath))
                }
            }
            tvCharacter.text = actor.name
        }
    }
}