package com.music.common.impl

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.music.common.R

class DefaultAppBar(val context: Context?) {

    private lateinit var topbarView: ViewGroup

    fun createAppBar(viewGroup: ViewGroup):DefaultAppBar {
        topbarView = LayoutInflater.from(context)?.inflate(R.layout.default_topbar, viewGroup, false) as ViewGroup
        return this
    }


    fun build():View = topbarView

    fun backgroundColor(color: Int) {
        topbarView.setBackgroundColor(color)
    }

    fun backgroundRes(res: Int) {
        topbarView.setBackgroundResource(res)
    }

    fun hideBack() {
        topbarView.findViewById<ImageView>(R.id.back)?.apply {
            visibility = View.GONE
        }
    }

    fun normalBack(call: View.OnClickListener = View.OnClickListener { if (context is Activity) context.finish() }) {
        topbarView.findViewById<ImageView>(R.id.back)?.apply {
            visibility = View.VISIBLE
            setOnClickListener(call)
        }
    }

    fun title(title: String) {
        topbarView.findViewById<TextView>(R.id.title)?.text = title
    }

    fun addActions(vararg views: View) {
        val trailing = topbarView.findViewById<FrameLayout>(R.id.trailing).apply { removeAllViews() }
        if (views.size > 1) {
            trailing.addView(LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
                views.forEach {
                    topbarView.addView(it)
                }
            })
        } else {
            trailing.addView(views.firstOrNull() ?: return)
        }
    }

    fun leading(view: View) {
        topbarView.findViewById<FrameLayout>(R.id.leading).apply {
            removeAllViews()
            addView(view)
        }
    }

    fun middle(view: View) {
        topbarView.findViewById<FrameLayout>(R.id.middle).apply {
            removeAllViews()
            addView(view)
        }
    }

    fun trailing(view: View) {
        topbarView.findViewById<FrameLayout>(R.id.trailing).apply {
            removeAllViews()
            addView(view)
        }
    }

    fun leading(viewRes: Int): View {
        val leading = topbarView.findViewById<FrameLayout>(R.id.leading).apply { removeAllViews() }
        return LayoutInflater.from(context).inflate(viewRes, leading)
    }

    fun middle(viewRes: Int): View {
        val middle = topbarView.findViewById<FrameLayout>(R.id.middle).apply { removeAllViews() }
        return LayoutInflater.from(context).inflate(viewRes, middle)
    }

    fun trailing(viewRes: Int): View {
        val trailing = topbarView.findViewById<FrameLayout>(R.id.trailing).apply { removeAllViews() }
        return LayoutInflater.from(context).inflate(viewRes, trailing)
    }

}