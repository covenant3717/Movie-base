package com.evgenykuksov.moviebase.screens.overview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.extansions.dp
import android.graphics.DashPathEffect
import android.graphics.Path
import android.util.Log
import com.evgenykuksov.moviebase.extansions.px

class RatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, R.color.overview_item_rank_active)
            strokeWidth = 4.dp
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE


/*
            val itemWidth = width / COUNT_ITEM
            val spaceWidth = (itemWidth / 10) * 4
            val itemWidthFinal = itemWidth - spaceWidth
            pathEffect = DashPathEffect(floatArrayOf(itemWidthFinal.toFloat(), spaceWidth.toFloat()), 0f)

            Log.i("ml", "==================================2340×1080")
            Log.i("ml", "width             : $width")
            Log.i("ml", "itemWidth         : $itemWidth")
            Log.i("ml", "spaceWidth        : $spaceWidth")
            Log.i("ml", "itemWidthFinal    : $itemWidthFinal")
*/
        }
    }
    private val path by lazy { Path() }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            val itemWidth = width / COUNT_ITEM
            val spaceWidth = (itemWidth / 10) * 4
            val itemWidthFinal = itemWidth - spaceWidth
            val spaceWidthFinal = spaceWidth + spaceWidth / COUNT_ITEM

            var startPointX = 2.dp
            var endPointX = itemWidthFinal.toFloat()

            do {
                path.moveTo(startPointX, (height / 2).toFloat())
                path.lineTo(endPointX, (height / 2).toFloat())
                it.drawPath(path, paint)

                startPointX += itemWidthFinal + spaceWidthFinal
                endPointX += itemWidthFinal + spaceWidthFinal
            } while (endPointX <= width)

//            Log.i("ml", "width          : $width")
//            Log.i("ml", "itemWidth      : $itemWidth")
//            Log.i("ml", "itemWidthFinal : $itemWidthFinal")
//            Log.i("ml", "spaceWidth     : $spaceWidth")
//            Log.i("ml", "spaceWidthFinal: $spaceWidthFinal")
//            Log.i("ml", "2.dp: ${2.dp}")
//            Log.i("ml", "=================================")

//            path.moveTo(2.dp, 2.dp)
//            path.lineTo(2.dp + width.toFloat(), 2.dp)
//            it.drawPath(path, paint)
        }
        super.onDraw(canvas)
    }
}

private const val COUNT_ITEM = 9