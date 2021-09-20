package com.evgenykuksov.moviebase.common.commonitems

import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.common_item_name.view.*

internal class NameItem(private val movieName: String) : Item() {

    override fun getLayout(): Int = R.layout.common_item_name

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvName.text = movieName
        }
    }
}