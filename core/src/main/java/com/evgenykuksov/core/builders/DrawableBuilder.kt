package com.evgenykuksov.core.builders

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.evgenykuksov.core.extensions.color
import com.evgenykuksov.core.extensions.dimen
import com.evgenykuksov.core.extensions.orZero

class DrawableBuilder {

    companion object Builder {

        @ColorRes
        private var colorRes: Int? = null

        @DimenRes
        private var leftTopRadiusRes: Int? = null

        @DimenRes
        private var rightTopRadiusRes: Int? = null

        @DimenRes
        private var leftBottomRadiusRes: Int? = null

        @DimenRes
        private var rightBottomRadiusRes: Int? = null

        @DimenRes
        private var leftCornersRadiusRes: Int? = null

        @DimenRes
        private var topCornersRadiusRes: Int? = null

        @DimenRes
        private var rightCornersRadiusRes: Int? = null

        @DimenRes
        private var bottomCornersRadiusRes: Int? = null

        @DimenRes
        private var allCornersRadiusRes: Int? = null

        fun color(@ColorRes colorRes: Int?) = apply { this.colorRes = colorRes }

        fun leftTopCorner(@DimenRes radiusRes: Int?) = apply { leftTopRadiusRes = radiusRes }
        fun rightTopCorner(@DimenRes radiusRes: Int?) = apply { rightTopRadiusRes = radiusRes }
        fun leftBottomCorner(@DimenRes radiusRes: Int?) = apply { leftBottomRadiusRes = radiusRes }
        fun rightBottomCorner(@DimenRes radiusRes: Int?) = apply { rightBottomRadiusRes = radiusRes }

        fun leftCorners(@DimenRes radiusRes: Int?) = apply { leftCornersRadiusRes = radiusRes }
        fun topCorners(@DimenRes radiusRes: Int?) = apply { topCornersRadiusRes = radiusRes }
        fun rightCorners(@DimenRes radiusRes: Int?) = apply { rightCornersRadiusRes = radiusRes }
        fun bottomCorners(@DimenRes radiusRes: Int?) = apply { bottomCornersRadiusRes = radiusRes }
        fun allCorners(@DimenRes radiusRes: Int?) = apply { allCornersRadiusRes = radiusRes }

        fun build(context: Context) = ShapeDrawable()
            .apply { setColor(colorRes, context) }
            .apply {
                // Скругление по отдельности
                setCornerRounding(
                    leftTopRadiusRes = leftTopRadiusRes,
                    rightTopRadiusRes = rightTopRadiusRes,
                    leftBottomRadiusRes = leftBottomRadiusRes,
                    rightBottomRadiusRes = rightBottomRadiusRes,
                    context
                )
            }
            .apply {
                // Скругление только левых углов
                setCornerRounding(
                    leftTopRadiusRes = leftCornersRadiusRes,
                    leftBottomRadiusRes = leftCornersRadiusRes,
                    context = context
                )
            }
            .apply {
                // Скругление только верхних углов
                setCornerRounding(
                    leftTopRadiusRes = topCornersRadiusRes,
                    rightTopRadiusRes = topCornersRadiusRes,
                    context = context
                )
            }
            .apply {
                // Скругление только правых углов
                setCornerRounding(
                    rightTopRadiusRes = rightCornersRadiusRes,
                    rightBottomRadiusRes = rightCornersRadiusRes,
                    context = context
                )
            }
            .apply {
                // Скругление только нижних углов
                setCornerRounding(
                    leftBottomRadiusRes = bottomCornersRadiusRes,
                    rightBottomRadiusRes = bottomCornersRadiusRes,
                    context = context
                )
            }
            .apply {
                // Скругление всех углов
                setCornerRounding(
                    leftTopRadiusRes = allCornersRadiusRes,
                    rightTopRadiusRes = allCornersRadiusRes,
                    leftBottomRadiusRes = allCornersRadiusRes,
                    rightBottomRadiusRes = allCornersRadiusRes,
                    context
                )
            }
            .also { cleaning() }

        private fun ShapeDrawable.setColor(colorRes: Int?, context: Context) {
            colorRes?.let { paint.color = context.color(it) }
        }

        private fun ShapeDrawable.setCornerRounding(
            leftTopRadiusRes: Int? = null,
            rightTopRadiusRes: Int? = null,
            leftBottomRadiusRes: Int? = null,
            rightBottomRadiusRes: Int? = null,
            context: Context
        ) {
            if (leftTopRadiusRes == null
                && rightTopRadiusRes == null
                && leftBottomRadiusRes == null
                && rightBottomRadiusRes == null
            ) return
            val leftTop = leftTopRadiusRes?.let { context.dimen(it).toFloat() }.orZero()
            val rightTop = rightTopRadiusRes?.let { context.dimen(it).toFloat() }.orZero()
            val leftBottom = leftBottomRadiusRes?.let { context.dimen(it).toFloat() }.orZero()
            val rightBottom = rightBottomRadiusRes?.let { context.dimen(it).toFloat() }.orZero()
            shape = RoundRectShape(
                floatArrayOf(leftTop, leftTop, rightTop, rightTop, rightBottom, rightBottom, leftBottom, leftBottom),
                null,
                null
            )
        }

        private fun cleaning() {
            colorRes = null
            leftTopRadiusRes = null
            rightTopRadiusRes = null
            leftBottomRadiusRes = null
            rightBottomRadiusRes = null
            topCornersRadiusRes = null
            bottomCornersRadiusRes = null
            allCornersRadiusRes = null
        }
    }
}