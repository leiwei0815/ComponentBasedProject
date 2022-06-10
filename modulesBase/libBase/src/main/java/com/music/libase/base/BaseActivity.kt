package com.music.libase.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.music.libase.api.*
import com.music.libase.provider.Action
import com.music.libase.provider.ActivityProvider

abstract class BaseActivity : AppCompatActivity(), TzLifecycleApi, ImageApi,
    PageStateApi, PopupApi, RouteApi, ToastApi, LogApi, LoadingApi,
    ActivityProvider, ReportApi {

    override val ctx: Context by lazy { this }

    open val contentView: View by lazy { layoutInflater.inflate(bindLayoutId(), null) }

    private val _initView by lazy { initView(contentView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultToast()
        setDefaultLoadingDialog()
        setDefaultView()
        setDefaultRouter()
        initData()
    }

    override fun onRetry() {
        pageState?.postValue(PageState.LOADING)
        initData()
    }

    private fun setDefaultView() {
        setContentView(
            Scaffold(ctx).apply {

                if (topBarView != null) setTopbar(BaseAppBar(topBarView!!))

                if (pageState == null) {
                    setContent(BaseContentView(contentView.apply { _initView }))

                } else {
                    pageState?.observe(this@BaseActivity) { t ->
                        setContent(
                            BaseContentView(
                                when (t) {
                                    PageState.LOADING -> loadingView()
                                    PageState.COMPLETE -> contentView.apply { _initView }
                                    PageState.FAIL -> errorView()
                                    PageState.RETRY -> retryView()
                                    else -> contentView.apply { _initView }
                                }
                            )
                        )
                    }
                }
            }
        )

    }

    private fun setDefaultLoadingDialog() {
        isShowLoading?.observe(this) {
            if (it == null) return@observe
            if (it)
                showLoadingDialog()
            else
                hideLoadingDialog()
        }
    }

    private fun setDefaultToast() {
        toastMsg?.observe(this) {
            if (it != null) toast(it)
        }
    }


    private fun setDefaultRouter() {
        router?.observe(this) {
            when (it.action) {
                Action.START -> if (it.params.isEmpty()) toActivity(it.name ?: "") else toActivity(it.name ?: "", it.params)
                Action.START_RESULT -> if (it.params.isEmpty()) toActivityForResult(it.name ?: "",it.code) else toActivityForResult(it.name ?: "", it.code,it.params)
                Action.FINISH -> if (it.name.isNullOrEmpty()) popActivity() else popToActivity(it.name)
                Action.REPLACE -> if (it.params.isEmpty()) replaceActivity(it.name ?: "") else replaceActivity(it.name ?: "", it.params)
                Action.MAIN -> popToMain()
                Action.RESULT -> popActivityForResult(it.params,it.code)
            }
        }
    }
}