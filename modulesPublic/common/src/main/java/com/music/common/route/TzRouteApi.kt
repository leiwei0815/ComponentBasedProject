package com.music.common.route

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.music.libase.api.RouteApi
import com.music.libase.base.Ctx
import com.music.libase.base.castContext
import java.io.Serializable

@JvmDefaultWithoutCompatibility
interface TzRouteApi : RouteApi {

    override fun toActivity(activity: String) {
        ARouter.getInstance().build(activity).navigation()
    }

    override fun toActivity(activity: String, map: MutableMap<String, Any>) {
        ARouter.getInstance().build(activity).apply {
            applyIntent(this, map)
        }.navigation()
    }


    override fun toActivityForResult(
        activity: String,
        requestCode: Int,
        map: MutableMap<String, Any>?,
    ) {
        ARouter.getInstance().build(activity).apply {
            if (map != null) {
                applyIntent(this, map)
            }
        }.navigation(ctx?.castActivity(), requestCode)
    }

    override fun popActivity() {
        ctx?.castActivity()?.finish()
    }


    override fun popActivityForResult(map: MutableMap<String, Any>, resultCode: Int) {
        val intent = Intent()
        map.forEach {
            intent.putExtra(it.key, it.value)
        }
        ctx?.castActivity()?.apply {
            setResult(resultCode, intent)
            finish()
        }
    }

    override fun popActivityForResult(resultCode: Int) {
        val intent = Intent()
        ctx?.castActivity()?.apply {
            setResult(resultCode, intent)
            finish()
        }
    }

    private fun Context.castActivity(): Activity? {
        if (this is Activity) return this
        if (this is Ctx) return this.castContext() as Activity
        return null
    }

    override fun popToMain() {
        popToActivity("/main/")
    }

    override fun popToActivity(activity: String) {
        ARouter.getInstance().build(activity)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .navigation()
    }

    override fun replaceActivity(activity: String) {
        toActivity(activity)
        popActivity()
    }

    override fun replaceActivity(activity: String, map: MutableMap<String, Any>) {
        toActivity(activity, map)
        popActivity()
    }


    private fun applyIntent(postcard: Postcard, map: MutableMap<String, Any>) {
        postcard.apply {
            map.forEach {
                when (it.value) {
                    is String -> withString(it.key, it.value as String)
                    is Int -> withInt(it.key, it.value as Int)
                    is Short -> withShort(it.key, it.value as Short)
                    is Boolean -> withBoolean(it.key, it.value as Boolean)
                    is Long -> withLong(it.key, it.value as Long)
                    is Double -> withDouble(it.key, it.value as Double)
                    is Byte -> withByte(it.key, it.value as Byte)
                    is Char -> withChar(it.key, it.value as Char)
                    is Float -> withFloat(it.key, it.value as Float)
                    is CharSequence -> withCharSequence(it.key, it.value as CharSequence)
                    is Parcelable -> withParcelable(it.key, it.value as Parcelable)
                    is Serializable -> withSerializable(it.key, it.value as Serializable)
                    is ByteArray -> withByteArray(it.key, it.value as ByteArray)
                    is ShortArray -> withShortArray(it.key, it.value as ShortArray)
                    is CharArray -> withCharArray(it.key, it.value as CharArray)
                    is FloatArray -> withFloatArray(it.key, it.value as FloatArray)
                    is Bundle -> withBundle(it.key, it.value as Bundle)
                    else -> {
                    }
                }
            }
        }
    }
}

private fun Intent.putExtra(key: String, value: Any) {
    when (value) {
        is String -> putExtra(key, value)
        is Int -> putExtra(key, value)
        is Short -> putExtra(key, value)
        is Boolean -> putExtra(key, value)
        is Long -> putExtra(key, value)
        is Double -> putExtra(key, value)
        is Byte -> putExtra(key, value)
        is Char -> putExtra(key, value)
        is Float -> putExtra(key, value)
        is CharSequence -> putExtra(key, value)
        is Parcelable -> putExtra(key, value)
        is Serializable -> putExtra(key, value)
        is ByteArray -> putExtra(key, value)
        is ShortArray -> putExtra(key, value)
        is CharArray -> putExtra(key, value)
        is FloatArray -> putExtra(key, value)
        is Bundle -> putExtra(key, value)
        else -> {
        }
    }
}
