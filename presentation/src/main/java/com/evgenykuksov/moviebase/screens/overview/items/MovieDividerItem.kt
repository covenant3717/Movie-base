package com.evgenykuksov.moviebase.screens.overview.items

import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

internal class MovieDividerItem : Item() {

    override fun getLayout(): Int = R.layout.item_divider_movie

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}