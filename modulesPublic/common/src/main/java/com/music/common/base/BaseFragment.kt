package com.music.common.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.music.common.mvp.BasePresenter
import com.music.common.mvp.IView
import com.music.libase.utils.ReflectUtil
import java.lang.Exception

/**
 * @Author   leiwei
 * @Date     2022/5/17
 * @Desc
 */
open class BaseFragment<T : ViewBinding> : Fragment(), IView {

    protected lateinit var binding: T

    protected lateinit var mContext: Activity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mContext = requireActivity()
        val rootView = inflectRootView()
        initViews()
        loadData()
        return rootView
    }

    /**
     * 弊端: 反射有点影响性能
     *
     * 通过反射进行 attach
     */
//    open fun inflectPresenters() {
//        try {
//            val fields = this::class.java.declaredFields
//            for (field in fields) {
//                field.isAccessible = true
//                if (BasePresenter::class.java.isAssignableFrom(field.type)) {
//                    val basePresenter = field.get(this) as BasePresenter<IView>
//                    basePresenter.attach(this)
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    open fun initViews() {

    }

    open fun loadData() {

    }

    /**
     * 通过反射获取到rootView
     *
     * @return View?
     */
    private fun inflectRootView(): View? {
        var rootView: View? = null
        val clz = ReflectUtil.analysisClassInfo(this)
        if (clz != ViewBinding::class.java && ViewBinding::class.java.isAssignableFrom(clz)) {
            try {
                val method = clz.getDeclaredMethod("inflate", LayoutInflater::class.java)
                method.isAccessible = true
                binding = method.invoke(null, layoutInflater) as T
                if (binding != null) {
                    rootView = binding.root
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return rootView
    }

    override fun showLoading(msg: String) {

    }

    override fun dismissLoading() {

    }

    override fun showToast(msg: String) {

    }

    override fun showLoadingLayout() {

    }

    override fun showSuccessLayout() {

    }

    override fun showErrorLayout() {

    }

    override fun showEmptyLayout() {

    }
}