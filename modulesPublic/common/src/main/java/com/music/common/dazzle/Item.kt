package com.music.common.dazzle

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import com.music.libase.api.CtxApi
import java.lang.reflect.ParameterizedType


abstract class Item<T> : CtxApi {

    override val ctx: Context? by lazy { context }

    var context: Context? = null

    //交叉轴的数量
    open val crossCount = 1

    //交叉轴的间距
    open val crossAxisSpacing = 0

    //主轴的间距
    open val mainAxisSpacing = 0

    open val padding: Rect = Rect(0, 0, 0, 0)

    //view的高度
    open val mainPixel = 0.0

    val type by lazy {
        val type = javaClass.genericSuperclass as ParameterizedType
        return@lazy type.actualTypeArguments.first().toString()
    }

    fun perfromInit(context: Context) {
        this.context = context
    }

    abstract fun itemCreate(context: Context, parent: ViewGroup): View

    abstract fun itemBuild(item: View?, index: Int, data: T)

    internal open fun performItemBuild(item: View?, index: Int, data: Any) {
        itemBuild(item, index, data as T)
    }
}
