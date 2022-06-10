package com.music.libase.utils

import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.math.roundToInt

/**
 * @Author   leiwei
 * @Date     2022/5/16
 * @Desc
 */
class ViewUtils {

}
val Int.dp:Int get() = (0.5f + this * Resources.getSystem().displayMetrics.density).toInt()

val Int.px:Int get() = (0.5f + this / Resources.getSystem().displayMetrics.density).roundToInt()

val Double.dp:Double get() = 0.5f + this * Resources.getSystem().displayMetrics.density

val Double.px:Double get() = 0.5f + this / Resources.getSystem().displayMetrics.density

val Float.dp:Float get() = 0.5f + this * Resources.getSystem().displayMetrics.density

val Float.px:Float get() = 0.5f + this / Resources.getSystem().displayMetrics.density

/**
 * 订阅到的点击事件在不停点击按钮后每5秒只会触发一次
 *
 * @receiver View
 * @return Flow<Unit>
 */
@ExperimentalCoroutinesApi
fun View.clickEventFlow(): Flow<Unit> {

    var lastClickTime = System.currentTimeMillis()

    return callbackFlow {
        setOnClickListener {
            if (System.currentTimeMillis() - lastClickTime > 5000) {
                lastClickTime = System.currentTimeMillis()
                offer(Unit)
            }
        }
        awaitClose {
            setOnClickListener(null)
        }
    }
}

/**
 * 在停止输入一秒后才发起网络请求，避免不必要的网络请求
 * 我们也可以使用Flow来实现这个功能。首先把TextView的文字变化转换成Flow
 *
 * @receiver TextView
 * @return Flow<CharSequence>
 */
@ExperimentalCoroutinesApi
fun TextView.textChangeFlow(): Flow<CharSequence> {

    return callbackFlow {

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(content: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (content != null) {
                    offer(content)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }

        addTextChangedListener(watcher)
        awaitClose {
            removeTextChangedListener(watcher)
        }
    }
}