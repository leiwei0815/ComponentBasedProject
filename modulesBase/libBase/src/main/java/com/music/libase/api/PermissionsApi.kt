package com.music.libase.api
import android.content.Context
import androidx.fragment.app.Fragment
@JvmDefaultWithoutCompatibility
interface PermissionsApi {

    fun Context.requestPermissionGrant(vararg permissions: String, onGrant: () -> Unit)

    fun Context.requestPermission(vararg permissions: String, call: PermissionCallback.() -> Unit)

    fun Fragment.requestPermissionGrant(vararg permissions: String, onGrant: () -> Unit)

    fun Fragment.requestPermission(vararg permissions: String, call: PermissionCallback.() -> Unit)

    fun Context.handPermission(vararg permissions: String, permissionTip:String,onGrant: () -> Unit)
    fun Fragment.handPermission(vararg permissions: String, permissionTip:String,onGrant: () -> Unit)

    class PermissionCallback {
        var grantCall: (() -> Unit)? = null
        var deniedCall: (() -> Unit)? = null
        var neverAskAgainCall: (() -> Unit)? = null

        //该权限被授予
        fun onPermissionGrant(call: () -> Unit) {
            grantCall=call
        }

        //该权限被拒绝
        fun onPermissionDenied(call: () -> Unit) {
            deniedCall=call
        }

        //该权限被拒绝并不在询问
        fun onNeverAskAgain(call: () -> Unit) {
            neverAskAgainCall=call
        }

    }
}
