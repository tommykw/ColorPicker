package com.github.tommykw.colorpicker

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by tommy on 2016/06/03.
 */
class ColorPicker constructor(context: Context,
                              attrs: AttributeSet?,
                              defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    private val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE)
    private val strokeSize = 2 * context.resources.displayMetrics.density
    private val rainbowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }
}