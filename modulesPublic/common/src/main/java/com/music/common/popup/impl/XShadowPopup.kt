package com.tz.popup.impl

import android.content.Context
import android.view.View
import com.lxj.xpopup.impl.PartShadowPopupView

class XShadowPopup(context: Context, private val layoutId:Int, private val call: View.()->Unit):
    PartShadowPopupView(context) {

}