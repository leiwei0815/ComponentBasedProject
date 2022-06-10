package com.music.common.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> itemCreateDataBinding(context: Context, parent: ViewGroup, @LayoutRes layoutRes: Int) =
    DataBindingUtil.bind<T>(LayoutInflater.from(context).inflate(layoutRes, parent, false))?.root
        ?: TextView(context).apply { text = "加载异常" }

fun <T : ViewDataBinding> itemNotifyDataBinding(item: View) = DataBindingUtil.getBinding<T>(item)