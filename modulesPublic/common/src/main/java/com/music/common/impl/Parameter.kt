package com.music.common.impl

object Parameter {
    const val SUCCESSCODE = "0"
    const val WX_LOGIN_CODE = "1062"
    const val WX_PAY_CODE = "1063"
    const val SUCCESSSTR = "SUCCESS"
    const val NOTLOGIN = "6101"
    const val NOTLOGINCODE = "V1016"
    const val TOKEN = "token"


    /**
     * 当前环境
     * */
    const val ENVIRONMENT = "ENVIRONMENT"

    /**
     * 延迟初始化
     * */
    const val LAZY_INIT = "LAZY_INIT"


    const val LOGIN_SUCCESS = "1012"

    const val LOGOUT = "1016"

    //账号切换成功
    const val CHANGE_SUCCESS = "1015"

    const val ERROR = "-1"


    /**
     * 需要显示关联手机号列表
     * */
    const val CHOICE_ACCOUNT = "1011"

    /**
     * 是否登录
     * */
    const val IS_LOGIN = "IS_LOGIN"

    /**
     * 用户真实姓名
     * */
    const val NICK_NAME = "nick_name"

    /**
     * 用户头像
     * */
    const val USER_ICON = "user_icon"

    /**
     * 用户UID
     * */
    const val USER_UID = "user_uid"

    /**
     * 用户账号
     * */
    const val USER_ACCOUNT = "user_account"

    /**
     * 用户手机号
     * */
    const val USER_PHONE = "user_phone"

    /**
     * 密码等级
     * */
    const val PASSWORD_LEVEL = "pass_WordLevel"

    /**
     * 认证手机号
     * */
    const val USER_CERT_PHONE = "cert_phone"

    /**
     * 验证码获取成功了
     * */
    const val SMS_CODE_OK = "0";

    /**
     * 需要图形验证码
     * */
    const val SMS_CODE_NEED_IMG = "V1027";

    /**
     * 图形验证码有问题，重新获取
     * */
    const val SMS_CODE_IMG_CODE_ERROR = "V1028";

    /**
     * 短信验证码超时，重新获取
     * */
    const val SMS_CODE_TIME = "V1037";

    /**
     * 短信验证码错了
     * */
    const val SMS_CODE_ERROR = "V1004";

    /**
     * 需要绑定手机号
     * */
    const val NEED_BIND_PHONE = "V1010";

    /**
     * 用于第一次保存用户兴趣
     * */
    const val FIRST_SAVE_INTEREST = "FIRST_SAVE_INTEREST"

    //wifi 提示 0 首次  1 本次应许 2 始终应许
     const val SP_NO_WIFI_PLAY = "no_wifi_play"
}