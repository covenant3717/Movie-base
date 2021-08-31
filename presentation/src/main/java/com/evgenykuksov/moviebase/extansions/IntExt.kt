package com.evgenykuksov.moviebase.extansions

fun Int?.isNotNull() = this != null

fun Int.insertSpaces(step: Int): String =
    this.toString()
        .toList()
        .reversed()
        .chunked(step)
        .map { it.joinToString(separator = "") }
        .joinToString(separator = " ")
        .reversed()