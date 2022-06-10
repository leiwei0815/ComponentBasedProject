package com.music.libase.base

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlin.math.roundToInt

class BaseContentView : FrameLayout {

    constructor(view: View, container: ViewGroup? = null) : super(view.context) {
        removeAllViews()
        if (view.parent != null) (view.parent as ViewGroup).removeView(view)
        addView(view, container?.layoutParams ?: LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }

    private constructor(context: Context) : super(context, null)
    private constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)
    private constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


}