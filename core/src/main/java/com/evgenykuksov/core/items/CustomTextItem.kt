package com.evgenykuksov.core.items

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat
import com.evgenykuksov.core.R
import com.evgenykuksov.core.extensions.color
import com.evgenykuksov.core.extensions.orZero
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.view_custom_text_item.view.*

/**
 * Notes:
 *
 * 1) "gravityState" example: Gravity.CENTER_HORIZONTAL
 *
 * */

data class CustomTextItem(
    private val textContent: String? = null,
    @StringRes private val textContentRes: Int? = null,
    @StyleRes private val styleRes: Int? = null,
    @ColorRes private val colorRes: Int? = null,
    @ColorRes private val backgroundColorRes: Int? = null,
    @DimenRes private val startPaddingRes: Int? = null,
    @DimenRes private val topPaddingRes: Int? = null,
    @DimenRes private val endPaddingRes: Int? = null,
    @DimenRes private val bottomPaddingRes: Int? = null,
    @DimenRes override val sidePaddingsRes: Int? = null,
    @DimenRes override val sideMarginsRes: Int? = null,
    private val gravityState: Int? = null,
    private val onClick: () -> Unit = {}
) : BaseItem(R.layout.view_custom_text_item) {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        super.bind(viewHolder, position)
        viewHolder.containerView.apply {
            textHint.apply {
                textContent?.let { text = it }
                textContentRes?.let { text = context.getString(it) }
                styleRes?.let { TextViewCompat.setTextAppearance(this, it) }
                colorRes?.let { setTextColor(context.color(it)) }
                backgroundColorRes?.let { setBackgroundColor(context.color(it)) }
                val startPadding = startPaddingRes?.let { resources.getDimensionPixelOffset(it) }.orZero()
                val topPadding = topPaddingRes?.let { resources.getDimensionPixelOffset(it) }.orZero()
                val endPadding = endPaddingRes?.let { resources.getDimensionPixelOffset(it) }.orZero()
                val bottomPadding = bottomPaddingRes?.let { resources.getDimensionPixelOffset(it) }.orZero()
                setPadding(startPadding, topPadding, endPadding, bottomPadding)
                gravityState?.let { gravity = it }
                setOnClickListener { onClick() }
            }
        }
    }
}