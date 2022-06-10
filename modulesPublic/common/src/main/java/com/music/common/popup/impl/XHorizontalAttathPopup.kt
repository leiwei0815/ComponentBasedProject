package com.tz.popup.impl

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.lxj.xpopup.core.HorizontalAttachPopupView

@SuppressLint("ViewConstructor")
class XHorizontalAttathPopup(context: Context, private val layoutId: Int, private val call: TzDialogAction) :
    HorizontalAttachPopupView(context) {


    init {
        call.popup = this
    }

    var contentView: View? = null

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