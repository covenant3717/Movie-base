package com.evgenykuksov.core.extensions

import com.xwray.groupie.kotlinandroidextensions.Item

fun Item.addTo(mutableList: MutableList<Item>) = mutableList.add(this)
