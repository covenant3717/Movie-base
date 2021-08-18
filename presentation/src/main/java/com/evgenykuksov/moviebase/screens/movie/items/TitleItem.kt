package com.evgenykuksov.moviebase.screens.movie.items

import androidx.annotation.StringRes
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.movie_item_title.view.*

internal class TitleItem(@StringRes private val stringRes: Int) : Item() {

    override fun getLayout(): Int = R.layout.movie_item_title

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvTitle.text = context.getString(stringRes)
        }
    }
}