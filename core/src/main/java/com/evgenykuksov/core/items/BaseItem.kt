package com.evgenykuksov.core.items

import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import com.evgenykuksov.core.extensions.dimen
import com.evgenykuksov.core.extensions.setMargins
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

open class BaseItem(
    @LayoutRes protected val rootView: Int,
    @DimenRes protected open val sidePaddingsRes: Int? = null,
    @DimenRes protected open val sideMarginsRes: Int? = null,
) : Item() {

    override fun getLayout(): Int = rootView

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            sidePaddingsRes?.let { setPadding(context.dimen(it), paddingTop, context.dimen(it), paddingBottom) }
            sideMarginsRes?.let { setMargins(startRes = sideMarginsRes, endRes = sideMarginsRes) }
        }
    }
}