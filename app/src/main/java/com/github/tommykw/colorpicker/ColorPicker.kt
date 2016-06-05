package com.github.tommykw.colorpicker

import android.content.Context
import android.graphics.Canvas
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

    private val rainbowBackgrondPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val pickPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pick = 0.5f
    private val verticalGridSize = 0f
    private val rainbowBaseline = 0f
    private var showPreview = false
    private val listener: OnColorChangedListener? = null

    override fun onDraw(canvas: Canvas) {
        drawPicker(canvas)
        drawColorAim(
                canvas,
                rainbowBaseline,
                verticalGridSize.toInt() / 2,
                verticalGridSize * 0.5f,
                color
        )
        if (showPreview) {
            drawColorAim(
                    canvas,
                    verticalGridSize,
                    (verticalGridSize / 1.4f).toInt(),
                    verticalGridSize * 0.7f,
                    color
            )
        }
    }

    private fun drawPicker(canvas: Canvas) {
        val x = verticalGridSize / 2f
        val y = rainbowBaseline.toFloat()
        rainbowPaint.strokeWidth = verticalGridSize / 1.5f + strokeSize
        rainbowBackgrondPaint.strokeWidth = rainbowPaint.strokeWidth + strokeSize
        canvas.drawLine(x, y, width - x, y, rainbowBackgrondPaint)
        canvas.drawLine(x, y, width - x, y, rainbowPaint)
    }

    private fun drawColorAim(canvas: Canvas, baseLine: Float, offset: Int, size: Float, color: Int) {
        val circleCenterX = offset + pick * (canvas.width - offset * 2)
        canvas.drawCircle(circleCenterX, baseLine, size, pickPaint.apply { this.color = Color.WHITE })
        canvas.drawCircle(circleCenterX, baseLine, size - strokeSize, pickPaint.apply { this.color = color })
    }

    fun color: Int get() = interpreterColor(pick, colors)

    interface OnColorChangedListener {
        fun onColorChanged(color: Int)
    }
}