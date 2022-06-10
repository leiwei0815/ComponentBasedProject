package com.tz.popup.impl

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.lxj.xpopup.core.BottomPopupView

@SuppressLint("ViewConstructor")
class XBottomPopup(context: Context, private val layoutId: Int, private val call: TzDialogAction) :
    BottomPopupView(context) {
    var contentView: View? = null

    init {
        call.popup = this
    }

    override fun getImplLayoutId(): Int {
        return layoutId
    }

    override fun addInnerContent() {
        contentView = LayoutInflater.from(context).inflate(implLayoutId, bottomPopupContainer, false)
        bottomPopupContainer.addView(contentView)
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