package com.evgenykuksov.moviebase.common.commonitems

import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.common_item_description.view.*

internal class DescriptionItem(private val description: String) : Item() {

    override fun getLayout(): Int = R.layout.common_item_description

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvDescription.text = description
        }
    }
}