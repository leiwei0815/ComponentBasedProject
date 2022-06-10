package com.music.common

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.music.common.glide.GlideImageApi
import com.music.common.impl.DefaultActivityProvider
import com.music.common.impl.DefaultLoadingApi
import com.music.common.impl.DefaultPageStateView
import com.music.common.log.TzLogApi
import com.tz.popup.TzPopupApi
import com.music.common.route.TzRouteApi
import com.music.common.storage.MMKVSPUtil
import com.music.common.toast.TzToastApi
import com.music.libase.base.BaseFragment
import com.music.libase.provider.BaseViewModel

abstract class TzBaseFragment : BaseFragment(), DefaultPageStateView, DefaultLoadingApi, GlideImageApi,
    TzLogApi,
    TzToastApi, TzPopupApi, TzRouteApi, MMKVSPUtil {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        setThemeDark(true)
    }
}

abstract class TzFragment<T : BaseViewModel> : TzBaseFragment(), DefaultActivityProvider<T>

abstract class TzDBFragment<T : BaseViewModel, P : ViewDataBinding> : TzFragment<T>() {

    open val mDataBinding by lazy {
        DataBindingUtil.bind<P>(super.contentView)?.apply { lifecycleOwner = this@TzDBFragment }
    }

    override val contentView: View by lazy { mDataBinding?.root ?: super.contentView }

    fun <I : ViewDataBinding> createDataBinding(@LayoutRes resId: Int): I? {
        return DataBindingUtil.bind<I>(layoutInflater.inflate(resId, null))
            ?.apply { lifecycleOwner = viewLifecycleOwner }
    }


}