package com.evgenykuksov.movie.items

import androidx.annotation.StringRes
import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_title.view.*

internal class TitleItem(@StringRes private val stringRes: Int) : Item() {

    override fun getLayout(): Int = R.layout.item_title

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvTitle.text = context.getString(stringRes)
        }
    }
}