package com.evgenykuksov.core.items

import com.evgenykuksov.core.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_name.view.*

class NameItem(private val movieName: String) : Item() {

    override fun getLayout(): Int = R.layout.item_name

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvName.text = movieName
        }
    }
}