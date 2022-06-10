package com.tz.popup.impl

import android.annotation.SuppressLint
import android.content.Context
import com.lxj.xpopup.core.DrawerPopupView

@SuppressLint("ViewConstructor")
class XDrawerPopup(context: Context, private val layoutId: Int, private val call: TzDialogAction) :
    DrawerPopupView(context) {
    init {
        call.popup = this
    }

    override fun getImplLayoutId(): Int {
        return layoutId
    }

    override fun onCreate() {
        super.onCreate()
        call.performCreate(popupImplView ?: this)
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