package com.music.libase.api

import android.view.View

interface PopupApi : CtxApi {

    fun showDialog(
        layoutId: Int,
        touchOutside: Boolean = true,
        backPressed: Boolean = true,
        shadowColor:Int=-1,
        call: DialogAction.() -> Unit
    ): DialogAction

    fun showBottomSheet(
        layoutId: Int,
        touchOutside: Boolean = true,
        backPressed: Boolean = true,
        shadowColor:Int=-1,
        call: DialogAction.() -> Unit
    ): DialogAction

    fun showDrawer(
        layoutId: Int,
        touchOutside: Boolean = true,
        backPressed: Boolean = true,
        shadowColor:Int=-1,
        call: DialogAction.() -> Unit
    ): DialogAction

    fun showFullDialog(
        layoutId: Int,
        touchOutside: Boolean = true,
        backPressed: Boolean = true,
        shadowColor:Int=-1,
        call: DialogAction.() -> Unit
    ): DialogAction

    fun showAttathDialog(
        attachToView:View,
        layoutId: Int,
        touchOutside: Boolean = true,
        backPressed: Boolean = true,
        shadowColor:Int=-1,
        offsetX:Int=0,
        offsetY:Int=0,
        call: DialogAction.() -> Unit
    ): DialogAction

}

interface DialogAction {

    fun show()

    fun dismiss()

    fun onCreate(call: View.() -> Unit)

    fun onShow(call: () -> Unit)

    fun onDismiss(call: () -> Unit)

    fun onBackPressed(call: () -> Boolean)




}
