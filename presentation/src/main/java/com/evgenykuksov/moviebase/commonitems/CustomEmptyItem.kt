package com.evgenykuksov.moviebase.commonitems

import androidx.annotation.DimenRes
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_custom_empty.view.*

data class CustomEmptyItem(@DimenRes private val size: Int? = null) : Item() {

    override fun getLayout(): Int = R.layout.item_custom_empty

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            size?.let {
                viewEmpty.layoutParams.height = context.resources.getDimensionPixelSize(it)
            }
        }
    }
}