package com.tz.popup.impl

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.lxj.xpopup.core.AttachPopupView

@SuppressLint("ViewConstructor")
class XAttathPopup(context: Context, private val layoutId: Int, private val call: TzDialogAction) :
    AttachPopupView(context) {
    var contentView: View? = null

    init {
        call.popup = this
    }

    override fun getImplLayoutId(): Int {
        return layoutId
    }

    override fun addInnerContent() {
        contentView = LayoutInflater.from(context).inflate(implLayoutId, attachPopupContainer, false)
        attachPopupContainer.addView(contentView)
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