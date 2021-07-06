package com.evgenykuksov.moviebase.screens.overview.items

import coil.load
import coil.transform.CircleCropTransformation
import com.evgenykuksov.domain.recipes.model.Recipe
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie.view.*

data class MovieItem(private val recipe: Recipe) : Item() {

    override fun getLayout(): Int = R.layout.item_movie

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            img.load(recipe.image) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }
}