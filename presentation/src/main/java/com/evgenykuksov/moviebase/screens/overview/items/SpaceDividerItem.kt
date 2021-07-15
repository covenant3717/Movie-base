package com.evgenykuksov.moviebase.screens.overview.items

import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

object SpaceDividerItem : Item() {

    override fun getLayout(): Int = R.layout.item_space_divider

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}