package com.evgenykuksov.moviebase.screens.movie.items

import com.evgenykuksov.moviebase.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.movie_item_cast.view.*

internal class CastItem(private val listItems: List<Item>) : Item() {

    override fun getLayout(): Int = R.layout.movie_item_cast

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            rvCast.apply {
                adapter = GroupAdapter<com.xwray.groupie.GroupieViewHolder>()
                    .also { it.addAll(listItems) }
            }
        }
    }
}