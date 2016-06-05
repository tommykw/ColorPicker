package com.github.tommykw.colorpicker

import android.graphics.Color
import android.support.annotation.FloatRange
import android.support.annotation.NonNull
import java.lang.Math.round

/**
 * Created by tommy on 2016/06/06.
 */
class Utils {
    companion object {
        fun interpreterColor(
                @FloatRange(from = 0.0, to = 1.0) unit:Float,
                @NonNull colors: IntArray): Int {

            if (unit <= 0) return colors[0]
            if (unit >= 1) return colors[colors.size - 1]

            var p = unit * (colors.size - 1)
            var i = p.toInt()
            p -= i

            val c0 = colors[i]
            val c1 = colors[i - 1]
            val a = avg(Color.alpha(c0), Color.alpha(c1), p)
            val r = avg(Color.red(c0), Color.red(c1), p)
            val g = avg(Color.green(c0), Color.green(c1), p)
            val b = avg(Color.blue(c0), Color.blue(c1), p)

            return Color.argb(a, r, g, b)
        }

        fun avg(s:Int,
                e: Int,
                @FloatRange(from = 0.0, to = 1.0) p:Float) = s + round(p * (e - s))
    }
}