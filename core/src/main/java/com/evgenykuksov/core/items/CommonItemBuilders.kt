package com.evgenykuksov.core.items

import androidx.annotation.DimenRes
import com.evgenykuksov.core.R
import com.evgenykuksov.core.extensions.addTo
import com.xwray.groupie.kotlinandroidextensions.Item

fun buildGroupLoadingItems(
    countItems: Int,
    @DimenRes itemWidthRes: Int?,
    @DimenRes itemHeightRes: Int?,
    @DimenRes itemCornerRoundRes: Int?,
    @DimenRes spaceBetweenItems: Int?,
    @DimenRes startEndSpacesRes: Int?,
) = mutableListOf<Item>().apply {
    CustomEmptyItem(widthRes = startEndSpacesRes).addTo(this)
    for (i in 1..countItems) {
        CustomLoadingItem(
            widthRes = itemWidthRes,
            heightRes = itemHeightRes,
            cornerRadiusRes = itemCornerRoundRes
        ).addTo(this)
        spaceBetweenItems?.let {
            if (i < countItems) CustomEmptyItem(widthRes = it).addTo(this)
        }
    }
    CustomEmptyItem(widthRes = startEndSpacesRes).addTo(this)
}