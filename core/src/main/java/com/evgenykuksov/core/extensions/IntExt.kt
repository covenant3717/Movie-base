package com.evgenykuksov.core.extensions

fun Int?.orZero() = this ?: 0

fun Int?.isNotNull() = this != null

fun Int.insertSpaces(step: Int): String =
    this.toString()
        .toList()
        .reversed()
        .chunked(step)
        .map { it.joinToString(separator = "") }
        .joinToString(separator = " ")
        .reversed()

fun Int.toHoursMinutes(): Pair<Int, Int> {
    val hours = this / 60
    val minutes = this % 60
    return Pair(hours, minutes)
}
