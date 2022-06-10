package com.music.common.impl

import android.graphics.Color
import com.music.common.R
import com.music.libase.api.DialogAction
import com.music.libase.api.LoadingApi
import com.tz.popup.TzPopupApi

var dialogAction: DialogAction? = null

interface DefaultLoadingApi : LoadingApi, TzPopupApi {

    override fun showLoadingDialog() {
        if(dialogAction!=null) dialogAction?.dismiss()
        dialogAction = showDialog(R.layout.loading_dialog,shadowColor = Color.TRANSPARENT) {}
    }

    override fun hideLoadingDialog() {
        dialogAction?.dismiss()
    }
}