package com.evgenykuksov.movie.items

import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.core.anim.setAnimScaleClickListener
import com.evgenykuksov.domain.movies.model.Trailer
import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_trailer.view.*

internal data class TrailerItem(
    private val trailer: Trailer,
    private val defaultImageLoader: ImageLoader,
    private val onClick: (trailer: Trailer) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.item_trailer

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgTrailer.apply {
                load(trailer.backdropPath, defaultImageLoader) {
                    transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_10)))
                }
            }
            setAnimScaleClickListener { onClick(trailer) }
        }
    }
}