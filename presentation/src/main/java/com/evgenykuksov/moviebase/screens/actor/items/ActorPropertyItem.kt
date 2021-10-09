package com.evgenykuksov.moviebase.screens.actor.items

import androidx.annotation.StringRes
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.core.extensions.string
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.actor_property_item.view.*

internal class ActorPropertyItem(
    @StringRes private val propertyName: Int,
    private val propertyValue: String,
) : Item() {

    override fun getLayout(): Int = R.layout.actor_property_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvPropertyName.text = context.string(propertyName)
            tvPropertyValue.text = propertyValue
        }
    }
}