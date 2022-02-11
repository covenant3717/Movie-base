package com.evgenykuksov.movie.items

import android.annotation.SuppressLint
import android.view.MotionEvent
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.core.anim.startAnimationScale
import com.evgenykuksov.domain.movies.model.MovieProvider
import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_provider.view.*

internal data class ProviderItem(
    private val provider: MovieProvider,
    private val defaultImageLoader: ImageLoader,
    private val onClick: (provider: MovieProvider) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.item_provider

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgProvider.apply {
                load(provider.logoPath, defaultImageLoader) {
                    transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_10)))
                }
            }
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> v.startAnimationScale(0.95f) {}
                    MotionEvent.ACTION_UP -> v.startAnimationScale(1f) { onClick(provider) }
                    MotionEvent.ACTION_CANCEL -> v.startAnimationScale(1f) {}
                    else -> {}
                }
                false
            }
        }
    }
}