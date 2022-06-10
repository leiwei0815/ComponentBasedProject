package com.music.common.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.music.common.R

/**
 *
 * @author     : liujianyou
 * @date : 2021/11/06 11:08
 * @Description:
 */
class EmptyAppbar(val context: Context?) {

    private lateinit var topbarView: View

    fun createAppBar(viewGroup: ViewGroup): EmptyAppbar {
        topbarView = LayoutInflater.from(context).inflate(R.layout.empty_topbar, viewGroup, false) as ViewGroup
        return this
    }

    fun build(): View = topbarView

    fun DefaultAppBar.backgroundColor(color: Int) {
        topbarView.setBackgroundColor(color)
    }

    fun DefaultAppBar.backgroundRes(res: Int) {
        topbarView.setBackgroundResource(res)
    }


}