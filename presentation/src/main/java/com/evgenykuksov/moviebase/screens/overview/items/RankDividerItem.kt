package com.evgenykuksov.moviebase.screens.overview.items

import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

internal class RankDividerItem : Item() {

    override fun getLayout(): Int = R.layout.item_divider_rank

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}