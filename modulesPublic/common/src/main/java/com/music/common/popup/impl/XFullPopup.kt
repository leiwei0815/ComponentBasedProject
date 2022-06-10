package com.tz.popup.impl

import android.annotation.SuppressLint
import android.content.Context
import com.lxj.xpopup.impl.FullScreenPopupView

@SuppressLint("ViewConstructor")
class XFullPopup(context: Context, private val layoutId: Int, private val call: TzDialogAction) :
    FullScreenPopupView(context) {
    init {
        call.popup = this
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