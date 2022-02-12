package com.evgenykuksov.movie.items

import android.annotation.SuppressLint
import androidx.navigation.fragment.FragmentNavigator
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.evgenykuksov.domain.movies.model.Worker
import com.evgenykuksov.movie.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_worker.view.*

internal data class WorkerItem(
    private val worker: Worker,
    private val defaultImageLoader: ImageLoader,
    private val onClick: (extras: FragmentNavigator.Extras) -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.item_worker

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.apply {
            imgWorker.apply {
                transitionName = worker.profilePath
                load(worker.profilePath, defaultImageLoader) {
                    transformations(RoundedCornersTransformation(resources.getDimension(R.dimen.dimen_14)))
                }
            }
            tvName.text = worker.name
            tvSpecialization.text = worker.job
        }
    }
}