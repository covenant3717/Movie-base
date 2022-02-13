package com.evgenykuksov.actor.items

import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.actor.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_actor_image.view.*

internal class ActorImageItem(
    private val imagePath: String,
    private val defaultImageLoader: ImageLoader,
    private val onClick: () -> Unit = {}
) : Item() {

    override fun getLayout(): Int = R.layout.item_actor_image

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgActor.apply {
                load(imagePath, defaultImageLoader) {
                    transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_10)))
                }
                setOnClickListener { onClick() }
            }
        }
    }
}