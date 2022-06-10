package com.music.libase.api

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.IntegerRes

interface ImageApi : CtxApi {


    fun <T> getDrawable(path: T, call: (Drawable?) -> Unit)


    fun <T> getGifDrawable(path: T, call: (Drawable?) -> Unit)

}

interface ImageApiKt : CtxApi {

    fun <T> ImageView.loadImage(path: T)

    fun <T> preloadImage(context: Context, path: T)

    fun <T> ImageView.loadImage(path: T, @IntegerRes placeholder: Int)

    fun <T> ImageView.loadImage(path: T, @IntegerRes placeholder: Int, @IntegerRes error: Int)

    fun <T> ImageView.loadImage(
        path: T,
        @IntegerRes placeholder: Int,
        @IntegerRes error: Int,
        radius: Int
    )

    suspend fun <T> getDrawable(path: T): Drawable

    suspend fun <T> getGifDrawable(path: T): Drawable
}
