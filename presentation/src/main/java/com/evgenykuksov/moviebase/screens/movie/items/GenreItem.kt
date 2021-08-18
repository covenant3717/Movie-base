package com.evgenykuksov.moviebase.screens.movie.items

import com.evgenykuksov.domain.movies.model.Genre
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.movie_item_genre.view.*

internal class GenreItem(private val listGenres: List<Genre>) : Item() {

    override fun getLayout(): Int = R.layout.movie_item_genre

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            listGenres
                .map { it.name }
                .joinToString(separator = ", ")
                .let { tvGenre.text = it }
        }
    }
}