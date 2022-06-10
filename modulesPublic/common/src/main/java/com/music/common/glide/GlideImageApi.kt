package com.music.common.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.music.common.glide.config.GlideCircleTransform
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import android.content.Context
import com.music.common.glide.config.GlideRoundTransform
import com.music.libase.api.ImageApi
import com.music.libase.api.ImageApiKt

import jp.wasabeef.glide.transformations.BlurTransformation




@JvmDefaultWithoutCompatibility
interface GlideImageApi : ImageApi, ImageApiKt {

    override fun <T> ImageView.loadImage(path: T) {
        loadImage(path, -1)
    }

    override fun <T> preloadImage(context:Context,path: T) {
        Glide.with(context).load(path).preload()
    }

    override fun <T> ImageView.loadImage(path: T, @DrawableRes placeholder: Int) {
        loadImage(path, placeholder, -1)
    }


    override fun <T> ImageView.loadImage(path: T, @DrawableRes placeholder: Int, @DrawableRes error: Int) {
        loadImage(path, placeholder, error, 0)
    }

    override fun <T> ImageView.loadImage(
        path: T, @DrawableRes placeholder: Int,
        @DrawableRes error: Int, radius: Int
    ) {
        Glide.with(ctx ?: return).load(path).transition(GenericTransitionOptions.withNoTransition())
            .apply(
                RequestOptions().placeholder(placeholder).error(error).apply {
                    if (radius < 0) {
                        transform(GlideCircleTransform())
                    } else if (radius > 0) {
                        transform(GlideRoundTransform(radius))
                    }
                }).into(this)
    }


    override suspend fun <T> getDrawable(path: T): Drawable = suspendCoroutine {
        Glide.with(ctx ?: return@suspendCoroutine).load(path)
            .transition(GenericTransitionOptions.withNoTransition())
            .into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    it.resume(resource)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    it.resumeWithException(CancellationException())
                }
            })
    }


    override fun <T> getDrawable(path: T, call: (Drawable?) -> Unit) {
        Glide.with(ctx ?: return).load(path)
            .transition(GenericTransitionOptions.withNoTransition())
            .into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    call.invoke(resource)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    call.invoke(errorDrawable)
                }
            })
    }


    override suspend fun <T> getGifDrawable(path: T): Drawable = suspendCoroutine {
        Glide.with(ctx ?: return@suspendCoroutine).asGif().load(path)
            .transition(GenericTransitionOptions.withNoTransition())
            .into(object : SimpleTarget<GifDrawable>() {

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    it.resumeWithException(CancellationException())
                }

                override fun onResourceReady(
                    resource: GifDrawable,
                    transition: Transition<in GifDrawable>?
                ) {
                    it.resume(resource)
                }
            })
    }

    override fun <T> getGifDrawable(path: T, call: (Drawable?) -> Unit) {
        Glide.with(ctx ?: return).asGif().load(path)
            .transition(GenericTransitionOptions.withNoTransition())
            .into(object : SimpleTarget<GifDrawable>() {
                override fun onResourceReady(
                    resource: GifDrawable,
                    transition: Transition<in GifDrawable>?
                ) {
                    call.invoke(resource)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    call.invoke(errorDrawable)
                }
            })
    }

}
//模糊图片
fun <T> ImageView.loadBlurImage(path: T) {
    Glide.with(context ?: return).load(path).override(150,150)
        .apply(RequestOptions.bitmapTransform(BlurTransformation(25) )).into(this)
}

fun <T> ImageView.loadImage(path: T) {
    loadImage(path, -1)
}

fun <T> ImageView.loadImage(path: T, @DrawableRes placeholder: Int) {
    loadImage(path, placeholder, -1)
}


fun <T> ImageView.loadImage(path: T, @DrawableRes placeholder: Int, @DrawableRes error: Int) {
    loadImage(path, placeholder, error, 0)
}

fun <T> ImageView.loadImage(
    path: T, @DrawableRes placeholder: Int,
    @DrawableRes error: Int, radius: Int
) {
    Glide.with(context ?: return).load(path).transition(GenericTransitionOptions.withNoTransition())
        .apply(
            RequestOptions().placeholder(placeholder).error(error).apply {
                if (radius < 0) {
                    transform(GlideCircleTransform())
                } else if (radius > 0) {
                    transform(GlideRoundTransform(radius))
                }
            }).into(this)
}