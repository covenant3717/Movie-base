package com.evgenykuksov.core.extensions

import com.evgenykuksov.core.regulars.Regulars

fun String.isCyrillicLetters(): Boolean = Regulars.CYRILLIC_LETTERS.toRegex().containsMatchIn(this)