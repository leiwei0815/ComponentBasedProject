package com.music.libase.base

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

open class ScreenAdapterView : FrameLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        Ctx(context.createConfigurationContext(Configuration().apply {
            setToDefaults()
        }).apply {
            resources.displayMetrics.density =
                resources.displayMetrics.widthPixels / (BaseApp.app?.displayWidth() ?: 375f)
        }, context),
        attrs,
        defStyleAttr
    )

}

class Ctx(var context: Context, private val oldContext: Context) : ContextWrapper(context) {

    fun getBeforeContext(): Context = oldContext

    fun getAppCompatActivity(): AppCompatActivity? {
        if (oldContext is AppCompatActivity) {
            return oldContext
        }
        return null
    }

    override fun getResources(): Resources {
        return context.resources
    }

    override fun getBaseContext(): Context {
        return context
    }
}

fun Context.castCtx(): Ctx? {
    if (this is Ctx) {
        return this
    }
    return null
}

fun Context.castContext(): Context {
    if (this is Ctx) {
        return this.getBeforeContext()
    }
    return this
}