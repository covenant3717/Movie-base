package com.evgenykuksov.moviebase.screens.overview.items

import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie.view.*

internal data class MovieItem(private val movie: Movie) : Item() {

    override fun getLayout(): Int = R.layout.item_movie

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgPoster.load(movie.posterPath) {
                crossfade(true)
                placeholder(R.drawable.ic_overview_popcorn)
                error(R.drawable.ic_overview_popcorn)
                transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_24)))
            }
        }
    }
}