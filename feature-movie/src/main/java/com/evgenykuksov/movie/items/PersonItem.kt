package com.evgenykuksov.movie.items

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.core.anim.startAnimationScale
import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_person.view.*

internal data class PersonItem(
    private val imagePath: String,
    private val defaultImageLoader: ImageLoader,
    private val onClick: (extras: FragmentNavigator.Extras) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.item_person

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgPerson.apply {
                transitionName = imagePath
                load(imagePath, defaultImageLoader) {
                    transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_14)))
                }
            }
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> v.startAnimationScale(0.95f) {}
                    MotionEvent.ACTION_UP -> v.startAnimationScale(1f) {
                        onClick(FragmentNavigatorExtras(imgPerson to imagePath))
                    }
                    MotionEvent.ACTION_CANCEL -> v.startAnimationScale(1f) {}
                    else -> {}
                }
                false
            }
        }
    }
}