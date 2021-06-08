package com.evgenykuksov.recipes.screens.main.items

import coil.load
import coil.transform.CircleCropTransformation
import com.evgenykuksov.domain.recipes.model.Recipe
import com.evgenykuksov.recipes.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_recipe.view.*

data class RecipeItem(private val recipe: Recipe) : Item() {

    override fun getLayout(): Int = R.layout.item_recipe

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            img.load(recipe.image) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }
}