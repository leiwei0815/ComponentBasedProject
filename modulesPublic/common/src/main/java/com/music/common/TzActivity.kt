package com.music.common

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.ImmersionBar
import com.music.common.glide.GlideImageApi
import com.music.common.impl.DefaultActivityProvider
import com.music.common.impl.DefaultLoadingApi
import com.music.common.impl.DefaultPageStateView
import com.music.common.impl.TzEventApi
import com.music.common.log.TzLogApi
import com.music.common.route.TzRouteApi
import com.music.common.storage.MMKVSPUtil
import com.music.common.toast.TzToastApi
import com.music.libase.base.BaseActivity
import com.music.libase.provider.BaseViewModel
import com.tz.popup.TzPopupApi


abstract class TzBaseActivity : BaseActivity(), DefaultPageStateView, DefaultLoadingApi, GlideImageApi, TzLogApi,
    TzEventApi,
    TzToastApi, TzPopupApi, TzRouteApi, MMKVSPUtil {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setThemeDark(true)
    }
}

abstract class TzActivity<T : BaseViewModel> : TzBaseActivity(), DefaultActivityProvider<T>

abstract class TzDBActivity<T : BaseViewModel, P : ViewDataBinding> : TzActivity<T>() {

    open val mDataBinding by lazy {
        DataBindingUtil.bind<P>(super.contentView)?.apply { lifecycleOwner = this@TzDBActivity }
    }

    override val contentView: View by lazy { mDataBinding?.root ?: super.contentView }

    fun <I : ViewDataBinding> createDataBinding(@LayoutRes resId: Int): I? {
        return DataBindingUtil.bind<I>(layoutInflater.inflate(resId, contentView as ViewGroup, false))
            ?.apply { lifecycleOwner = this@TzDBActivity }
    }


}

fun Activity.setThemeDark(isDark: Boolean) {
    ImmersionBar.with(this).apply { statusBarDarkFont(isDark) }.init()
}

fun Fragment.setThemeDark(isDark: Boolean) {
    ImmersionBar.with(this).apply { statusBarDarkFont(isDark) }.init()
}