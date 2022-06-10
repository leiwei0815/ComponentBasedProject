package com.tz.popup.impl

import android.view.View
import com.lxj.xpopup.core.BasePopupView
import com.music.libase.api.DialogAction


class TzDialogAction() : DialogAction {

    private var createCall: (View.() -> Unit)? = null
    private var showCall: (() -> Unit)? = null
    private var dismissCall: (() -> Unit)? = null
    private var backCall: (() -> Boolean)? = null

    var popup: BasePopupView? = null

    override fun show() {
        popup?.show()
    }

    override fun dismiss() {
        popup?.dismiss()
    }

    override fun onCreate(call: View.() -> Unit) {
        createCall = call
    }

    override fun onShow(call: () -> Unit) {
        showCall = call
    }

    override fun onDismiss(call: () -> Unit) {
        dismissCall = call
    }

    override fun onBackPressed(call: () -> Boolean) {
        backCall = call
    }

    fun performCreate(view: View) = createCall?.invoke(view)

    fun performShow() = showCall?.invoke()

    fun performDismiss() = dismissCall?.invoke()

    fun performBackPressed(): Boolean = backCall?.invoke() ?: false

}