package com.tz.popup.impl

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import com.lxj.xpopup.core.CenterPopupView

@SuppressLint("ViewConstructor")
class XCenterPopup(context: Context, private val layoutId: Int, private val call: TzDialogAction) :
    CenterPopupView(context) {
    init {
        call.popup = this
    }

    override fun getMaxHeight(): Int {
        return 0
    }

    override fun getMaxWidth(): Int {
        return 0
    }

    override fun getImplLayoutId(): Int {
        return layoutId
    }

    override fun onCreate() {
        super.onCreate()
        call.performCreate(contentView ?: this)
    }

    override fun onShow() {
        super.onShow()
        call.performShow()
    }

    override fun onDismiss() {
        super.onDismiss()
        call.performDismiss()
    }

}