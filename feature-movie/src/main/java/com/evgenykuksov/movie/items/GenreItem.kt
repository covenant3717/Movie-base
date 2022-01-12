package com.evgenykuksov.movie.items

import com.evgenykuksov.core.extensions.color
import com.evgenykuksov.domain.movies.model.Genre
import com.evgenykuksov.movie.R
import com.google.android.material.chip.Chip
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_genre.view.*

internal class GenreItem(private val listGenres: List<Genre>) : Item() {

    override fun getLayout(): Int = R.layout.item_genre

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            listGenres.forEach {
                Chip(context)
                    .apply {
                        text = it.name
                        setChipBackgroundColorResource(R.color.item_genre_back)
                        setTextColor(context.color(R.color.item_genre_text))
                        setEnsureMinTouchTargetSize(false)
                    }
                    .let { chipGroup.addView(it) }
            }
        }
    }
}