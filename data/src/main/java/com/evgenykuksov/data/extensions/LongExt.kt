package com.evgenykuksov.data.extensions

fun Long?.orNegativeDefault() = this ?: -1L