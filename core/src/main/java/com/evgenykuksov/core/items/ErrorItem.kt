package com.evgenykuksov.core.items

import com.evgenykuksov.core.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class ErrorItem : Item() {

    override fun getLayout(): Int = R.layout.item_error

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}