package com.evgenykuksov.core.items

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DimenRes
import com.evgenykuksov.core.R
import com.evgenykuksov.core.extensions.dimen
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_custom_one_line_loading.view.*

class CustomLoadingItem(
    private val widthLayoutParams: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    private val heightLayoutParams: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    @DimenRes private val widthRes: Int? = null,
    @DimenRes private val heightRes: Int? = null,
    @DimenRes private val marginStartRes: Int? = null,
    @DimenRes private val marginEndRes: Int? = null,
    @DimenRes private val marginStartEndRes: Int? = null,
    @DimenRes private val cornerRadiusRes: Int? = null,
) : Item() {

    override fun getLayout(): Int = R.layout.item_custom_one_line_loading

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            layoutParams = FrameLayout.LayoutParams(widthLayoutParams, heightLayoutParams)
            widthRes?.let { cardView.layoutParams.width = context.dimen(it) }
            heightRes?.let { cardView.layoutParams.height = context.dimen(it) }
            marginStartEndRes?.let {
                val margin = resources.getDimensionPixelOffset(it)
                (cardView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(margin, 0, margin, 0)
            }
            marginStartRes?.let {
                val margin = resources.getDimensionPixelOffset(it)
                (cardView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(margin, 0, margin, 0)
            }
            marginEndRes?.let {
                val margin = resources.getDimensionPixelOffset(it)
                (cardView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(margin, 0, margin, 0)
            }
            cornerRadiusRes?.let { cardView.radius = context.dimen(it).toFloat() }
        }
    }
}