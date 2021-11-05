package com.evgenykuksov.core.items

import android.view.ViewGroup
import androidx.annotation.DimenRes
import com.evgenykuksov.core.R
import com.evgenykuksov.core.extensions.dimen
import com.evgenykuksov.core.extensions.orZero
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_custom_one_line_loading.view.*

class CustomSingleLineLoadingItem(
    @DimenRes private val widthRes: Int? = null,
    @DimenRes private val heightRes: Int? = null,
    @DimenRes private val marginStartRes: Int? = null,
    @DimenRes private val marginEndRes: Int? = null,
) : Item() {

    override fun getLayout(): Int = R.layout.item_custom_one_line_loading

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            widthRes?.let { cardView.layoutParams.width = context.dimen(it) }
            heightRes?.let { cardView.layoutParams.height = context.dimen(it) }
            val marginStart = marginStartRes?.let { resources.getDimensionPixelOffset(it) }.orZero()
            val marginEnd = marginEndRes?.let { resources.getDimensionPixelOffset(it) }.orZero()
            (cardView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(marginStart, 0, marginEnd, 0)
        }
    }
}