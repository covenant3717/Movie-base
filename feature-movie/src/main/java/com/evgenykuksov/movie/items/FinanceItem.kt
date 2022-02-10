package com.evgenykuksov.movie.items

import com.evgenykuksov.core.extensions.insertSpaces
import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_finance.view.*

internal class FinanceItem(
    private val revenue: Int,
    private val costs: Int
) : Item() {

    override fun getLayout(): Int = R.layout.item_finance

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvRevenue.text = context.getString(R.string.item_title_revenue, revenue.insertSpaces(3))
            tvCosts.text = context.getString(R.string.item_title_costs, costs.insertSpaces(3))
        }
    }
}