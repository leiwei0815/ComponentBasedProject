package com.music.common.impl

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.music.libase.base.BaseApp
import com.music.libase.base.Ctx
import com.music.common.dazzle.Item

inline fun <reified T : ViewModel> Application.viewModels(): Lazy<T> = lazy {
    return@lazy ViewModelProvider.AndroidViewModelFactory(this).create(T::class.java)
}

inline fun <reified T : ViewModel> ComponentActivity.viewModels(): Lazy<T> = lazy {
    return@lazy ViewModelProvider(
        viewModelStore,
        ViewModelProvider.AndroidViewModelFactory(application)
    ).get(T::class.java)
}

//只能在fragment单一的情况下才能使用，如果是多个fragment的viewmodel是同一个 那就会状态有问题
inline fun <reified V : Fragment, reified T : ViewModel> Item<*>.fragmentViewModels(): Lazy<T?> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    if (ctx == null) return@lazy null
    if (ctx is AppCompatActivity || ctx is Ctx) {
        val act = when (ctx) {
            is AppCompatActivity -> ctx as AppCompatActivity
            is Ctx -> (ctx as Ctx).getAppCompatActivity()
            else -> null
        }
        act!!.supportFragmentManager.fragments.first { it is V }.apply {
            return@lazy ViewModelProvider(
                viewModelStore,
                ViewModelProvider.AndroidViewModelFactory(requireContext().applicationContext as Application)
            ).get(T::class.java)
        }
    }
    null
}

inline fun <reified T : ViewModel> Item<*>.viewModels(): Lazy<T?> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    when {
        ctx is ComponentActivity -> {
            return@lazy ViewModelProvider(
                (ctx as ComponentActivity).viewModelStore,
                ViewModelProvider.AndroidViewModelFactory(ctx?.applicationContext as Application)
            ).get(T::class.java)
        }
        ctx is Ctx -> {
            return@lazy ViewModelProvider(
                (ctx as Ctx).getAppCompatActivity()?.viewModelStore ?: return@lazy null,
                ViewModelProvider.AndroidViewModelFactory(ctx?.applicationContext as Application)
            ).get(T::class.java)
        }
        ctx is Application -> {
            return@lazy ViewModelProvider.AndroidViewModelFactory(ctx as Application).create(T::class.java)
        }
        BaseApp.app != null && BaseApp.app is Application -> {
            return@lazy ViewModelProvider.AndroidViewModelFactory(BaseApp.app as Application).create(T::class.java)
        }
        else -> {
            return@lazy null
        }
    }

}

inline fun <reified T : ViewModel> Fragment.viewModels(): Lazy<T> = lazy {
    return@lazy ViewModelProvider(
        viewModelStore,
        ViewModelProvider.AndroidViewModelFactory(requireContext().applicationContext as Application)
    ).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.activityViewModels(): Lazy<T> = lazy {
    return@lazy ViewModelProvider(
        (activity as ComponentActivity).viewModelStore,
        ViewModelProvider.AndroidViewModelFactory(requireContext().applicationContext as Application)
    ).get(T::class.java)
}