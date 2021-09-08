package com.evgenykuksov.moviebase.screens.movie.items

import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

internal class ActorLoadingItem : Item() {

    override fun getLayout(): Int = R.layout.movie_item_actor_loading

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}