package com.evgenykuksov.moviebase.screens.overview.items

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie.view.*

data class RankItem(@ColorRes private val colorBackground: Int) : Item() {

    override fun getLayout(): Int = R.layout.item_rank

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            rankLine.setCardBackgroundColor(ContextCompat.getColor(context, colorBackground))
        }
    }
}