package com.evgenykuksov.moviebase.common.commonitems

import androidx.annotation.DimenRes
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.common_item_custom_empty.view.*

data class CustomEmptyItem(
    @DimenRes private val heightRes: Int? = null,
    @DimenRes private val widthRes: Int? = null
) : Item() {

    override fun getLayout(): Int = R.layout.common_item_custom_empty

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            heightRes?.let { viewEmpty.layoutParams.height = context.resources.getDimension(it).toInt() }
            widthRes?.let { viewEmpty.layoutParams.width = context.resources.getDimension(it).toInt() }
        }
    }
}