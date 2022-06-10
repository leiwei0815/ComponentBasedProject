package com.music.common.glide.config

import android.graphics.*
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import java.security.MessageDigest

/**
 * Created by chao.zhang on 2017/3/10.
 */
class GlideRoundTransform(dp: Int) : BitmapTransformation() {
    public override fun transform(
        pool: BitmapPool, toTransform: Bitmap,
        outWidth: Int, outHeight: Int
    ): Bitmap {
        return roundCrop(pool, toTransform)!!
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}

    companion object {
        private var radius = 0f
        private fun roundCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
            if (source == null) return null
            var result: Bitmap? = pool[source.width, source.height, Bitmap.Config.ARGB_8888]
            if (result == null) {
                result = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
            }
            val canvas = Canvas(result!!)
            val paint = Paint()
            paint.shader =
                BitmapShader(source, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
            paint.isAntiAlias = true
            val rectF = RectF(
                0f, 0f, source.width.toFloat(), source.height
                    .toFloat()
            )
            canvas.drawRoundRect(rectF, radius, radius, paint)
            return result
        }
    }

    init {
        radius = dp.toFloat()
    }
}