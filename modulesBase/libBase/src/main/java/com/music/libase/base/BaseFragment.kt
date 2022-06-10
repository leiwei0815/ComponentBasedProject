package com.music.libase.base

import android.content.Context
import android.content.res.XmlResourceParser
import android.graphics.Color
import android.os.Bundle
import android.util.Xml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.music.libase.api.*
import com.music.libase.provider.Action
import com.music.libase.provider.ActivityProvider
import java.lang.Exception

abstract class BaseFragment : Fragment(), TzLifecycleApi, ImageApi,
    PageStateApi, PopupApi, RouteApi, ToastApi, LogApi, LoadingApi,
    ActivityProvider, ReportApi {

    //请注意使用
    override val ctx: Context? by lazy { context }

    open val contentView: View by lazy { layoutInflater.inflate(bindLayoutId(), null) }

    private val _initView by lazy { initView(contentView) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDefaultToast()
        setDefaultLoadingDialog()
        setDefaultRouter()
        return Scaffold(requireContext()).apply {

            if (topBarView != null) setTopbar(BaseAppBar(topBarView!!))

            if (pageState == null) {
                setContent(BaseContentView(contentView.apply { _initView },container))
            } else {
                pageState?.observe(viewLifecycleOwner) { t ->
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
    }

    override fun onRetry() {
        pageState?.postValue(PageState.LOADING)
        initData()
    }

    private fun setDefaultLoadingDialog() {
        isShowLoading?.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            if (it)
                showLoadingDialog()
            else
                hideLoadingDialog()
        }
    }

    private fun setDefaultToast() {
        toastMsg?.observe(viewLifecycleOwner) {
            if (it != null) toast(it)
        }
    }

    private fun setDefaultRouter() {
        router?.observe(viewLifecycleOwner) {
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