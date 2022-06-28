package com.evgenykuksov.core.base

import com.xwray.groupie.kotlinandroidextensions.Item

interface BaseNavigation {

    fun back()

    fun toYoutube(videoKey: String)

    fun toBottomDialog(listItems: List<Item>)
}