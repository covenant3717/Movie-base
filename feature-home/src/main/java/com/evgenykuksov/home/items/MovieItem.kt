package com.evgenykuksov.home.items

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.annotation.DimenRes
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
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
    @DimenRes private val roundCornerRes: Int?,
    private val onClick: (movie: Movie, extras: FragmentNavigator.Extras) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.item_movie

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgPoster.apply {
                transitionName = movie.posterPath
                load(movie.posterPath, defaultImageLoader) {
                    roundCornerRes?.let {
                        transformations(RoundedCornersTransformation(resources.getDimension(it)))
                    }
                }
            }
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> v.startAnimationScale(0.95f) {}
                    MotionEvent.ACTION_UP -> v.startAnimationScale(1f) {
                        onClick(
                            movie,
                            FragmentNavigatorExtras(imgPoster to movie.posterPath)
                        )
                    }
                    MotionEvent.ACTION_CANCEL -> v.startAnimationScale(1f) {}
                    else -> {}
                }
                false
            }
        }
    }
}