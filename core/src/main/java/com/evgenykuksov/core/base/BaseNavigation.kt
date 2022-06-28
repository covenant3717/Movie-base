package com.evgenykuksov.core.base

interface BaseNavigation {

    fun back()

    fun toYoutube(videoKey: String)

    fun toBottomDialog(listItems: List<*>)
}