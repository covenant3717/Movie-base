package com.evgenykuksov.home.utils

import androidx.annotation.StringRes
import com.evgenykuksov.home.R

enum class MoviesCategory(@StringRes val titleRes: Int, val index: Int) {
    UPCOMING(R.string.tab_upcoming, 0),
    NOW_PLAYING(R.string.tab_new, 1),
    POPULAR(R.string.tab_popular, 2),
    TOP_RATED(R.string.tab_top_rated, 3);

    companion object {

        fun getCategoryByIndex(index: Int): MoviesCategory = MoviesCategory.values().getOrElse(index) { UPCOMING }
    }
}
