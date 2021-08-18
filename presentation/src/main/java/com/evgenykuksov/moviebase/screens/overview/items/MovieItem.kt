package com.evgenykuksov.moviebase.screens.overview.items

import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.overview_item_movie.view.*

internal data class MovieItem(
    val movie: Movie,
    private val defaultImageLoader: ImageLoader
) : Item() {

    override fun getLayout(): Int = R.layout.overview_item_movie

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgPoster.load(movie.posterPath, defaultImageLoader) {
                transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_24)))
            }
        }
    }
}