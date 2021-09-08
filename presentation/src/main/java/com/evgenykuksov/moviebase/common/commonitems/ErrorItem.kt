package com.evgenykuksov.moviebase.common.commonitems

import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

internal class ErrorItem : Item() {

    override fun getLayout(): Int = R.layout.common_item_error

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}