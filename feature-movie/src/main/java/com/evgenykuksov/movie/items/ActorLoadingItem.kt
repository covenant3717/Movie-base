package com.evgenykuksov.movie.items

import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

internal class ActorLoadingItem : Item() {

    override fun getLayout(): Int = R.layout.item_actor_loading

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}