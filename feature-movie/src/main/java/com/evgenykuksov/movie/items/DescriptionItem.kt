package com.evgenykuksov.movie.items

import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_description.view.*

class DescriptionItem(private val description: String) : Item() {

    override fun getLayout(): Int = R.layout.item_description

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvDescription.text = description
        }
    }
}