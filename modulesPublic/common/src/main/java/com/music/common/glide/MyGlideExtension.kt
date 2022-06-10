package com.music.common.glide

import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions
import com.music.common.R
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * @Author   leiwei
 * @Date     2022/5/16
 * @Desc
 */
@GlideExtension
open class MyGlideExtension private constructor(){

    companion object {

        /**
         * 设置头像
         * @param options BaseRequestOptions<*>
         * @param size Int
         * @return BaseRequestOptions<*>
         */
        @JvmStatic
        @GlideOption
        fun applyAvatar(options: BaseRequestOptions<*>, size: Int): BaseRequestOptions<*> {
            val transformation = BlurTransformation(15,1) //冲突 .circleCrop()
            return options
                .placeholder(R.mipmap.img_avatar_default)
                .error(R.mipmap.img_avatar_default)
                .override(size)
                .circleCrop()
//                .fitCenter()
//                .centerCrop()
//                .transform(transformation)
//                .transform(MultiTransformation(transformation,CircleCrop())) //多做transform 使用transform
        }

        /**
         *
         * @param options BaseRequestOptions<*>
         * @param size Int    圆角大小
         * @return BaseRequestOptions<*>
         */
        @JvmStatic
        @GlideOption
        fun applyRound(options: BaseRequestOptions<*>, size: Int): BaseRequestOptions<*> {
            //Glide设置图片圆角角度
            val roundedCorners = RoundedCorners(size)
            //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
            //val options = RequestOptions.bitmapTransform(roundedCorners).override(20, 20);
            val option = RequestOptions.bitmapTransform(roundedCorners)
            return options
                .placeholder(R.mipmap.img_avatar_default)
                .error(R.mipmap.img_avatar_default)
                .apply(option)
        }
    }
}