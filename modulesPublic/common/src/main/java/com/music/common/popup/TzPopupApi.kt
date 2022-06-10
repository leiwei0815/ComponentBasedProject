package com.tz.popup

import android.graphics.Color
import android.view.View
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.SimpleCallback
import com.music.libase.api.DialogAction
import com.music.libase.api.PopupApi
import com.tz.popup.impl.*

interface TzPopupApi : PopupApi {
    override fun showDialog(
        layoutId: Int,
        touchOutside: Boolean,
        backPressed: Boolean,
        shadowColor: Int,
        call: DialogAction.() -> Unit
    ): DialogAction {
        val action = TzDialogAction()
        call.invoke(action)
        if (shadowColor != -1) {
            XPopup.setShadowBgColor(shadowColor)
        } else {
            XPopup.setShadowBgColor(Color.parseColor("#7F000000"))
        }
        XPopup.Builder(ctx)
            .setPopupCallback(object : SimpleCallback() {
                override fun onBackPressed(popupView: BasePopupView?): Boolean {
                    return action.performBackPressed()
                }
            })
            .hasShadowBg(shadowColor != Color.TRANSPARENT)
            .dismissOnBackPressed(backPressed)
            .dismissOnTouchOutside(touchOutside)
            .asCustom(XCenterPopup(ctx!!, layoutId, action)).show()
        return action
    }

    override fun showBottomSheet(
        layoutId: Int,
        touchOutside: Boolean,
        backPressed: Boolean,
        shadowColor: Int,
        call: DialogAction.() -> Unit
    ): DialogAction {
        val action = TzDialogAction()
        call.invoke(action)
        if (shadowColor != -1) {
            XPopup.setShadowBgColor(shadowColor)
        } else {
            XPopup.setShadowBgColor(Color.parseColor("#7F000000"))
        }
        XPopup.Builder(ctx)
            .setPopupCallback(object : SimpleCallback() {
                override fun onBackPressed(popupView: BasePopupView?): Boolean {
                    return action.performBackPressed()
                }
            })
            .hasShadowBg(shadowColor != Color.TRANSPARENT)
            .autoFocusEditText(false)
            .dismissOnBackPressed(backPressed)
            .dismissOnTouchOutside(touchOutside)
            .asCustom(XBottomPopup(ctx!!, layoutId, action)).show()
        return action
    }

    override fun showDrawer(
        layoutId: Int,
        touchOutside: Boolean,
        backPressed: Boolean,
        shadowColor: Int,
        call: DialogAction.() -> Unit
    ): DialogAction {
        val action = TzDialogAction()
        call.invoke(action)
        if (shadowColor != -1) {
            XPopup.setShadowBgColor(shadowColor)
        } else {
            XPopup.setShadowBgColor(Color.parseColor("#7F000000"))
        }
        XPopup.Builder(ctx)
            .setPopupCallback(object : SimpleCallback() {
                override fun onBackPressed(popupView: BasePopupView?): Boolean {
                    return action.performBackPressed()
                }
            })
            .hasShadowBg(shadowColor != Color.TRANSPARENT)
            .dismissOnBackPressed(backPressed)
            .dismissOnTouchOutside(touchOutside)
            .asCustom(XDrawerPopup(ctx!!, layoutId, action)).show()

        return action
    }

    override fun showFullDialog(
        layoutId: Int,
        touchOutside: Boolean,
        backPressed: Boolean,
        shadowColor: Int,
        call: DialogAction.() -> Unit
    ): DialogAction {
        val action = TzDialogAction()
        call.invoke(action)
        if (shadowColor != -1) {
            XPopup.setShadowBgColor(shadowColor)
        } else {
            XPopup.setShadowBgColor(Color.parseColor("#7F000000"))
        }
        XPopup.Builder(ctx)
            .setPopupCallback(object : SimpleCallback() {
                override fun onBackPressed(popupView: BasePopupView?): Boolean {
                    return action.performBackPressed()
                }
            })
            .hasShadowBg(shadowColor != Color.TRANSPARENT)
            .dismissOnBackPressed(backPressed)
            .dismissOnTouchOutside(touchOutside)
            .asCustom(XFullPopup(ctx!!, layoutId, action)).show()
        return action
    }

    override fun showAttathDialog(
        attachToView: View,
        layoutId: Int,
        touchOutside: Boolean,
        backPressed: Boolean,
        shadowColor: Int,
        offsetX: Int,
        offsetY: Int,
        call: DialogAction.() -> Unit
    ): DialogAction {
        val action = TzDialogAction()
        call.invoke(action)
        if (shadowColor != -1) {
            XPopup.setShadowBgColor(shadowColor)
        } else {
            XPopup.setShadowBgColor(Color.parseColor("#7F000000"))
        }
        XPopup.Builder(ctx)
            .setPopupCallback(object : SimpleCallback() {
                override fun onBackPressed(popupView: BasePopupView?): Boolean {
                    return action.performBackPressed()
                }

            })
            .hasShadowBg(shadowColor != Color.TRANSPARENT)
            .dismissOnBackPressed(backPressed)
            .dismissOnTouchOutside(touchOutside)
            .offsetX(offsetX)
            .offsetY(offsetY)
            .atView(attachToView).asCustom(XAttathPopup(ctx!!, layoutId, action)).show()
        return action
    }

    fun showHorizontalAttathDialog(
        attachToView: View,
        layoutId: Int,
        touchOutside: Boolean = true,
        backPressed: Boolean = true,
        shadowColor: Int = -1,
        call: DialogAction.() -> Unit
    ): DialogAction {
        val action = TzDialogAction()
        call.invoke(action)
        if (shadowColor != -1) {
            XPopup.setShadowBgColor(shadowColor)
        } else {
            XPopup.setShadowBgColor(Color.parseColor("#7F000000"))
        }
        XPopup.Builder(ctx)
            .setPopupCallback(object : SimpleCallback() {
                override fun onBackPressed(popupView: BasePopupView?): Boolean {
                    return action.performBackPressed()
                }
            })
            .hasShadowBg(shadowColor != Color.TRANSPARENT)
            .dismissOnBackPressed(backPressed)
            .dismissOnTouchOutside(touchOutside)
            .atView(attachToView).asCustom(XHorizontalAttathPopup(ctx!!, layoutId, action)).show()
        return action
    }
}

