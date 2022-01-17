package com.evgenykuksov.core.items

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evgenykuksov.core.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_custom_group.view.*

class CustomGroupItem(
    private val items: List<Item>,
    @RecyclerView.Orientation private val orientation: Int = RecyclerView.HORIZONTAL
) : Item() {

    override fun getLayout(): Int = R.layout.item_custom_group

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            rvGroup.apply {
                layoutManager = LinearLayoutManager(context, orientation, false)
                adapter = GroupAdapter<com.xwray.groupie.GroupieViewHolder>()
                    .also { it.addAll(items) }
            }
        }
    }
}