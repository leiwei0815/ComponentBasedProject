package com.music.libase.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlin.math.roundToInt

class BaseAppBar : ViewGroup {
    private var _statusBarHeight = 0.0

    constructor(view: View) : super(view.context) {
        removeAllViews()
        if (view.parent != null) (view.parent as ViewGroup).removeView(view)
        addView(view)
    }

    private constructor(context: Context?) : super(context)
    private constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    private constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setStatusBarHeight(height: Double) {
        _statusBarHeight = height
        getChildAt(0).setPadding(0,height.roundToInt(),0,0)
    }

    fun isTransparent(): Boolean {

        if (getChildAt(0).background == null) return true

        if (getChildAt(0).background is ColorDrawable) {
            return (getChildAt(0).background as ColorDrawable).color == Color.TRANSPARENT
        }

        return false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        if (childCount > 0) {
            setMeasuredDimension(getChildAt(0).measuredWidth, getChildAt(0).measuredHeight)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (index in 0 until childCount) {
            getChildAt(index).layout(l, t, r, b)
        }
    }
}