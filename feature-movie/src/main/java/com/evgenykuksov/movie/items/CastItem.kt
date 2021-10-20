package com.evgenykuksov.movie.items

import com.evgenykuksov.movie.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_cast.view.*

internal class CastItem(private val listItems: List<Item>) : Item() {

    override fun getLayout(): Int = R.layout.item_cast

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            rvCast.apply {
                adapter = GroupAdapter<com.xwray.groupie.GroupieViewHolder>()
                    .also { it.addAll(listItems) }
            }
        }
    }
}