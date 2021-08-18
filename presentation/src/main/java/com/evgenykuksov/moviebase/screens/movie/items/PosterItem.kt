package com.evgenykuksov.moviebase.screens.movie.items

import coil.ImageLoader
import coil.load
import com.evgenykuksov.domain.movies.model.MovieDetails
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.movie_item_poster.view.*

internal class PosterItem(
    private val movieDetails: MovieDetails,
    private val defaultImageLoader: ImageLoader
) : Item() {

    override fun getLayout(): Int = R.layout.movie_item_poster

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgPoster.load(movieDetails.posterPath, defaultImageLoader)
        }
    }
}