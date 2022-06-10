package com.music.common.toast

import android.widget.Toast
import com.music.libase.api.ToastApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

private var toast: Toast? = null

interface TzToastApi : ToastApi {

    override fun toast(msg: Any) {
        when (msg) {
            is Int -> {
                var string =""
                try {
                    string = ctx?.resources?.getString(msg).toString()
                }catch (e:Exception){

                }
                if (string.isNotEmpty()) postToast(string)
                else postToast(msg.toString())

            }
            is String -> {
                if (msg.isNotEmpty()) postToast(msg)

            }
            else -> {
                postToast(msg.toString())
            }
        }
    }

    fun postToast(msg: String) {
        GlobalScope.launch(Dispatchers.Main) {
            if (toast != null) toast?.cancel()
            toast = Toast.makeText(ctx, null, Toast.LENGTH_SHORT).apply {
                setText(msg)
                show()
            }
        }

    }

    override fun globalToast(msg: Any) {
        val app = ctx?.applicationContext
        if (toast != null) toast?.cancel()
        when (msg) {
            is Int -> {
                var string =""
                try {
                    string = ctx?.resources?.getString(msg).toString()
                }catch (e:Exception){

                }
                toast = if (string.isNotEmpty())  {
                    Toast.makeText(app, null, Toast.LENGTH_SHORT).apply {
                        setText(string)
                        show() }
                }else{
                    Toast.makeText(app, null, Toast.LENGTH_SHORT).apply {
                        setText(msg.toInt())
                        show() }
                }
            }
            is String -> {
                if (msg.isEmpty()) return
                toast = Toast.makeText(app, null, Toast.LENGTH_SHORT).apply {
                    setText(msg)
                    show()
                }
            }
            else -> {
                toast = Toast.makeText(app, null, Toast.LENGTH_SHORT).apply {
                    setText(msg.toString())
                    show() }
            }
        }
    }


  /*  override fun postToast(msg: String) {
        GlobalScope.launch(Dispatchers.Main) { toast(msg) }
    }*/

}