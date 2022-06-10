package com.music.libase.base

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import kotlin.math.roundToInt

class Scaffold : ScreenAdapterView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setTopbar(topbar: BaseAppBar) {
        for (index in 0 until childCount) {
            if (getChildAt(index) is BaseAppBar) removeViewAt(index)
        }
        addView(topbar)
    }

    fun setContent(contentView: BaseContentView) {
        for (index in 0 until childCount) {
            if (getChildAt(index) is BaseContentView) removeViewAt(index)
        }
        addView(contentView, 0, LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val count = childCount
        if (count > 1) {
            val view = getChildAt(1) as BaseAppBar
            if (!view.isTransparent()) {
                getChildAt(0).layout(
                    left,
                    view.bottom,
                    right,
                    bottom
                )
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val count = childCount
        if (count > 1) {
            val view = getChildAt(1) as BaseAppBar
            if (!view.isTransparent()) {
                val statusHeight = getStatusBarHeight()
                view.setStatusBarHeight(statusHeight.toDouble())
                measureChild(view, widthMeasureSpec, heightMeasureSpec)
                val spec = MeasureSpec.makeMeasureSpec(
                    MeasureSpec.getSize(heightMeasureSpec) - view.measuredHeight,
                    MeasureSpec.getMode(heightMeasureSpec),
                )
                measureChild(getChildAt(0),widthMeasureSpec,spec)
                setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec))
                return
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        //获取状态栏高度的资源id
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}