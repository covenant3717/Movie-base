package com.evgenykuksov.core.extensions

fun Long?.orNegativeDefault() = this ?: -1L