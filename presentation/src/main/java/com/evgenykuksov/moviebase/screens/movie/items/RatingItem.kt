package com.evgenykuksov.moviebase.screens.movie.items

import com.evgenykuksov.core.extensions.insertSpaces
import com.evgenykuksov.moviebase.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.movie_item_rating.view.*

internal class RatingItem(
    private val voteAverage: Float,
    private val voteCount: Int
) : Item() {

    override fun getLayout(): Int = R.layout.movie_item_rating

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            tvVoteAverage.text = voteAverage.toString()
            tvVoteCount.text = resources.getString(R.string.movie_item_vote_count, voteCount.insertSpaces(3))
            ratingBar.rating = calcRatingStars()
        }
    }

    private fun calcRatingStars() = MAX_RATING_STARS * voteAverage / MAX_RATING_PROGRESS
}

private const val MAX_RATING_STARS = 5f
private const val MAX_RATING_PROGRESS = 10f