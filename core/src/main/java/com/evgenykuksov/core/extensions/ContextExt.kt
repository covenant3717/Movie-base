package com.evgenykuksov.core.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, this.resources.getText(resId), duration).show()

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Context.color(@ColorRes color: Int): Int = ContextCompat.getColor(this, color)

fun Context.string(@StringRes string: Int): String = this.getString(string)

fun Context.drawable(@DrawableRes drawable: Int): Drawable? = ContextCompat.getDrawable(this, drawable)

fun Context.dimen(@DimenRes dimen: Int): Int = this.resources.getDimensionPixelOffset(dimen)

fun Context.integer(@IntegerRes integer: Int): Int = this.resources.getInteger(integer)

fun Context.buildBackgroundDrawable(
    @ColorRes colorRes: Int? = null,
    @DimenRes leftTopRadiusRes: Int? = null,
    @DimenRes rightTopRadiusRes: Int? = null,
    @DimenRes leftBottomRadiusRes: Int? = null,
    @DimenRes rightBottomRadiusRes: Int? = null,
    @DimenRes topCornersRadiusRes: Int? = null,
    @DimenRes bottomCornersRadiusRes: Int? = null,
    @DimenRes allCornersRadiusRes: Int? = null
) = ShapeDrawable()
    .apply { colorRes?.let { paint.color = color(it) } }
    .apply {
        // Скругление углов по отдельности
        if (leftTopRadiusRes == null
            && rightTopRadiusRes == null
            && leftBottomRadiusRes == null
            && rightBottomRadiusRes == null
        ) return@apply
        val leftTopRadius: Float = leftTopRadiusRes?.let { dimen(leftTopRadiusRes).toFloat() }.orZero()
        val rightTopRadius: Float = rightTopRadiusRes?.let { dimen(rightTopRadiusRes).toFloat() }.orZero()
        val leftBottomRadius: Float = leftBottomRadiusRes?.let { dimen(leftBottomRadiusRes).toFloat() }.orZero()
        val rightBottomRadius: Float = rightBottomRadiusRes?.let { dimen(rightBottomRadiusRes).toFloat() }.orZero()
        shape = RoundRectShape(
            floatArrayOf(
                leftTopRadius, leftTopRadius,
                rightTopRadius, rightTopRadius,
                leftBottomRadius, leftBottomRadius,
                rightBottomRadius, rightBottomRadius
            ),
            null,
            null
        )
    }
    .apply {
        // Скругление только верхних углов
        topCornersRadiusRes?.let {
            val topRadius: Float = dimen(it).toFloat()
            shape = RoundRectShape(
                floatArrayOf(topRadius, topRadius, topRadius, topRadius, 0f, 0f, 0f, 0f), null, null
            )
        }
    }
    .apply {
        // Скругление только нижних углов
        bottomCornersRadiusRes?.let {
            val bottomRadius: Float = dimen(it).toFloat()
            shape = RoundRectShape(
                floatArrayOf(0f, 0f, 0f, 0f, bottomRadius, bottomRadius, bottomRadius, bottomRadius), null, null
            )
        }
    }
    .apply {
        // Скругление всех углов
        allCornersRadiusRes?.let {
            val radius: Float = dimen(it).toFloat()
            shape = RoundRectShape(
                floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius), null, null
            )
        }
    }