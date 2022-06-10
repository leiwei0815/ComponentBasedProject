package com.music.common.impl

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.music.common.R
import com.music.libase.api.PageStateApi
import com.music.libase.utils.dp

interface DefaultPageStateView : PageStateApi {
    override fun loadingView(): View {
        return FrameLayout(ctx!!).apply {
            addView(ProgressBar(ctx), FrameLayout.LayoutParams(50.dp, 50.dp).apply { gravity = Gravity.CENTER })
            layoutParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun errorView(): View {
        return LayoutInflater.from(ctx!!).inflate(R.layout.empty_layout, null)
    }

    override fun retryView(): View {
        return LayoutInflater.from(ctx!!).inflate(R.layout.retry_layout, null).apply {
            setOnClickListener {
                onRetry()
            }
        }
    }

}