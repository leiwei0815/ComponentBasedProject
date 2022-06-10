package com.music.common.impl

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.forEachIndexed
import com.music.common.R
import kotlin.math.roundToInt

class FlowLayout : ViewGroup {
    private var mainSpace = 0.0f
    private var crossSpace = 0.0f
    private val childRect by lazy { mutableListOf<RectF>() }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout)
            mainSpace = ta.getDimension(R.styleable.FlowLayout_mainSpace, 0.0f)
            crossSpace = ta.getDimension(R.styleable.FlowLayout_crossSpace, 0.0f)
            ta.recycle()
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        childRect.forEachIndexed { index, rectF ->
            getChildAt(index).layout(
                rectF.left.roundToInt(), rectF.top.roundToInt(), rectF.right.roundToInt(),
                rectF.bottom.roundToInt()
            )
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        childRect.clear()
        val hModel = MeasureSpec.getMode(heightMeasureSpec)
        val wSize = MeasureSpec.getSize(widthMeasureSpec)
        val hSize = MeasureSpec.getSize(heightMeasureSpec)
        var oldW = paddingLeft.toFloat()
        var oldH = paddingTop.toFloat()
        var maxHeight = 0
        forEachIndexed { index, it ->
            measureChild(it, widthMeasureSpec, heightMeasureSpec)
            val nextWidth = oldW + it.measuredWidth + paddingRight
            if ((nextWidth+mainSpace) > wSize) {
                oldH += maxHeight + (if (index == 0) 0f else crossSpace)
                oldW = paddingLeft.toFloat()
                maxHeight = 0
            } else if (index != 0) {
                oldW += mainSpace
            }
            if (maxHeight < it.measuredHeight) {
                maxHeight = it.measuredHeight
            }
            val rect = RectF(oldW, oldH, oldW + it.measuredWidth, oldH + it.measuredHeight)

            childRect.add(rect)

            oldW += it.measuredWidth

        }

        if (hModel == MeasureSpec.EXACTLY) {
            setMeasuredDimension(wSize, hSize)
        } else {
            setMeasuredDimension(wSize, (oldH + maxHeight + paddingBottom + 0.5f).roundToInt())
        }

    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

}