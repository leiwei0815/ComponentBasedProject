package com.music.common.impl

import android.net.Uri
import com.music.common.network.TzNetworkApi
import com.music.common.network.toEntity
import java.io.File
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object OSSConfig {
    val OSS_URL: String get() = ""
}

val String.ossUrl get() = "${OSSConfig.OSS_URL}${this}"

class OSSUpload : TzNetworkApi {
    var model: ResOSSModel? = null


    private suspend fun initOSS() {
        model = get(
            "api/v1/public/upload/getRefactorUploadToken".ossUrl,
            mapOf("dirPrefix" to "com.tanzhou.singer")
        ).toEntity<TzBaseModel<ResTokenModel>>()?.data?.ossToken ?: return
    }

    suspend fun uploadFile(path: String): OSSFileModel? {
        if (model == null) initOSS()
        if (model == null) throw Exception("图片上传错误")

        //文件名
        val fileName = encode(path)
        val suffixList = path.split(".")
        val suffix = "." + suffixList.last()
        //创建一个formdata，作为dio的参数
        val map = mapOf(
            "key" to "${model?.dir}/$fileName$suffix",
            "policy" to model?.policy,
            "OSSAccessKeyId" to model?.accessid,
            "success_action_status" to "200", //让服务端返回200，不然，默认会返回204
            "Signature" to model?.signature,
            "Expires" to model?.expire,
            "file" to File(path)
        )

        try {
            var res = post(model?.host ?: "", map)
            return presignPublicURL("${model?.dir}/$fileName$suffix")
        } catch (e: Exception) {

        }
        return null
    }

    private fun presignPublicURL(fileStr: String): OSSFileModel {
        val rPath = Uri.encode(fileStr.trim())
            .replace("+", "%20")
            .replace("*", "%2A")
            .replace("%7E", "~")
            .replace("%2F", "/")

        return OSSFileModel("${model?.frontDomain}$rPath", "/$rPath")
    }

    private fun encode(password: String): String {
        try {
            val instance: MessageDigest = MessageDigest.getInstance("MD5")//获取md5加密对象
            val digest: ByteArray = instance.digest(password.toByteArray())//对字符串加密，返回字节数组
            var sb = StringBuffer()
            for (b in digest) {
                var i: Int = b.toInt() and 0xff//获取低八位有效值
                var hexString = Integer.toHexString(i)//将整数转化为16进制
                if (hexString.length < 2) {
                    hexString = "0$hexString"//如果是一位的话，补0
                }
                sb.append(hexString)
            }
            return sb.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }
}

data class OSSFileModel(
    val absolutePath: String = "",
    val relativePath: String = ""
)

class ResTokenModel(
    val ossToken: ResOSSModel
)

class ResOSSModel(
    val accessid: String,
    val policy: String,
    val signature: String,
    val dir: String,
    val host: String,
    val expire: String,
    val frontDomain: String,
)