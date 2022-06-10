package com.music.common.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.LibraryGlideModule
import java.io.InputStream


/**
 * @Author   leiwei
 * @Date     2022/5/16
 * @Desc     为glide配置第三方的网络请求库的支持
 */
@GlideModule
open class OkHttpLibraryGlideModule : LibraryGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        registry.replace(GlideUrl::class.java,InputStream::class.java,OkHttpUrlLoader.Factory())
    }
}