package com.evgenykuksov.home.items

import android.annotation.SuppressLint
import android.view.MotionEvent
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.core.anim.startAnimationScale
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.home.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_movie.view.*

internal data class MovieItem(
    private val movie: Movie,
    private val defaultImageLoader: ImageLoader,
    private val onClick: (movieId: Long) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.item_movie

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgPoster.load(movie.posterPath, defaultImageLoader) {
                transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_24)))
            }
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> v.startAnimationScale(0.9f) {}
                    MotionEvent.ACTION_UP -> v.startAnimationScale(1f) { onClick(movie.id) }
                    MotionEvent.ACTION_CANCEL -> v.startAnimationScale(1f) {}
                    else -> {
                    }
                }
                false
            }
        }
    }
}