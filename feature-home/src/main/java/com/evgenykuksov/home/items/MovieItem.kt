package com.evgenykuksov.home.items

import androidx.annotation.DimenRes
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.core.anim.setAnimScaleClickListener
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
            setAnimScaleClickListener {
                onClick(movie, FragmentNavigatorExtras(imgPoster to movie.posterPath))
            }
        }
    }
}