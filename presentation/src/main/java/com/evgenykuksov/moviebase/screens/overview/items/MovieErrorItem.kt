package com.evgenykuksov.moviebase.screens.overview.items

import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

internal class MovieErrorItem : Item() {

    override fun getLayout(): Int = R.layout.overview_item_error

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}