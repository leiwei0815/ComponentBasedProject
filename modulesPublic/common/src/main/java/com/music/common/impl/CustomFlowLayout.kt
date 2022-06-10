package com.music.common.impl

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.LayoutDirection
import android.view.View
import android.view.ViewGroup
import androidx.core.text.TextUtilsCompat
import com.music.common.R
import java.util.*

/**
 *
 * @author     : liujianyou
 * @date : 2021/10/23 15:03
 * @Description:
 */
@SuppressLint("CustomViewStyleable")
class CustomFlowLayout(context: Context, attrs: AttributeSet?, defStyle: Int) :
    ViewGroup(context, attrs, defStyle) {
    private var mAllViews: MutableList<MutableList<View>> = ArrayList<MutableList<View>>()
    private var mLineHeight: MutableList<Int> = ArrayList()
    private var mLineWidth: MutableList<Int> = ArrayList()
    private var mGravity: Int
    private var lineViews: MutableList<View> = ArrayList()

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val sizeWidth: Int = MeasureSpec.getSize(widthMeasureSpec)
        val modeWidth: Int = MeasureSpec.getMode(widthMeasureSpec)
        val sizeHeight: Int = MeasureSpec.getSize(heightMeasureSpec)
        val modeHeight: Int = MeasureSpec.getMode(heightMeasureSpec)

        // wrap_content
        var width = 0
        var height = 0
        var lineWidth = 0
        var lineHeight = 0
        val cCount: Int = childCount
        for (i in 0 until cCount) {
            val child: View = getChildAt(i)
            if (child.visibility == View.GONE) {
                if (i == cCount - 1) {
                    width = lineWidth.coerceAtLeast(width)
                    height += lineHeight
                }
                continue
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val lp: MarginLayoutParams = child
                .layoutParams as MarginLayoutParams
            val childWidth: Int = (child.measuredWidth + lp.leftMargin
                    + lp.rightMargin)
            val childHeight: Int = (child.measuredHeight + lp.topMargin
                    + lp.bottomMargin)
            if (lineWidth + childWidth > sizeWidth - paddingLeft - paddingRight) {
                width = width.coerceAtLeast(lineWidth)
                lineWidth = childWidth
                height += lineHeight
                lineHeight = childHeight
            } else {
                lineWidth += childWidth
                lineHeight = lineHeight.coerceAtLeast(childHeight)
            }
            if (i == cCount - 1) {
                width = lineWidth.coerceAtLeast(width)
                height += lineHeight
            }
        }
        setMeasuredDimension( //
            if (modeWidth == MeasureSpec.EXACTLY) sizeWidth else width + paddingLeft + paddingRight,
            if (modeHeight == MeasureSpec.EXACTLY) sizeHeight else height + paddingTop + paddingBottom //
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mAllViews.clear()
        mLineHeight.clear()
        mLineWidth.clear()
        lineViews.clear()
        val width: Int = width
        var lineWidth = 0
        var lineHeight = 0
        val cCount: Int = childCount
        for (i in 0 until cCount) {
            val child: View = getChildAt(i)
            if (child.visibility == View.GONE) continue
            val lp: MarginLayoutParams = child
                .layoutParams as MarginLayoutParams
            val childWidth: Int = child.measuredWidth
            val childHeight: Int = child.measuredHeight
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - paddingLeft - paddingRight) {
                mLineHeight.add(lineHeight)
                mAllViews.add(lineViews)
                mLineWidth.add(lineWidth)
                lineWidth = 0
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin
                lineViews = ArrayList<View>()
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin
            lineHeight = lineHeight.coerceAtLeast(childHeight + lp.topMargin
                    + lp.bottomMargin)
            lineViews.add(child)
        }
        mLineHeight.add(lineHeight)
        mLineWidth.add(lineWidth)
        mAllViews.add(lineViews)
        var left: Int = paddingLeft
        var top: Int = paddingTop
        val lineNum = mAllViews.size
        for (i in 0 until lineNum) {
            lineViews = mAllViews[i]
            lineHeight = mLineHeight[i]

            // set gravity
            val currentLineWidth = mLineWidth[i]
            when (mGravity) {
                LEFT -> {
                    left = paddingLeft
                }
                CENTER -> {
                    left = (width - currentLineWidth) / 2 + paddingLeft
                }
                RIGHT -> {
                    //  适配了rtl，需要补偿一个padding值
                    left = width - (currentLineWidth + paddingLeft) - paddingRight
                    //  适配了rtl，需要把lineViews里面的数组倒序排
                    lineViews.reverse()
                }
            }
            for (j in lineViews.indices) {
                val child: View = lineViews[j]
                if (child.visibility == View.GONE) {
                    continue
                }
                val lp: MarginLayoutParams = child
                    .layoutParams as MarginLayoutParams
                val lc: Int = left + lp.leftMargin
                val tc: Int = top + lp.topMargin
                val rc: Int = lc + child.measuredWidth
                val bc: Int = tc + child.measuredHeight
                child.layout(lc, tc, rc, bc)
                left += (child.measuredWidth + lp.leftMargin
                        + lp.rightMargin)
            }
            top += lineHeight
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    companion object {
        private const val LEFT = -1
        private const val CENTER = 0
        private const val RIGHT = 1
    }

    init {
        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.TagFlowLayout)
        mGravity = ta.getInt(R.styleable.TagFlowLayout_tag_gravity, LEFT)
        val layoutDirection: Int = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault())
        if (layoutDirection == LayoutDirection.RTL) {
            mGravity = if (mGravity == LEFT) {
                RIGHT
            } else {
                LEFT
            }
        }
        ta.recycle()
    }
}