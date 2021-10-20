package com.evgenykuksov.actor.items

import androidx.annotation.StringRes
import com.evgenykuksov.actor.R
import com.evgenykuksov.core.extensions.string
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_property.view.*

internal class ActorPropertyItem(
    @StringRes private val propertyName: Int,
    private val propertyValue: String,
) : Item() {

    override fun getLayout(): Int = R.layout.item_property

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvPropertyName.text = context.string(propertyName)
            tvPropertyValue.text = propertyValue
        }
    }
}