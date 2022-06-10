package com.music.libase.utils

import java.lang.reflect.ParameterizedType

/**
 * @Author   leiwei
 * @Date     2022/5/11
 * @Desc
 */
object ReflectUtil {

    /**
     * 通过反射获取object的第一个泛型参数
     * @param obj Any
     * @return Class<*>
     */
     fun analysisClassInfo(obj: Any): Class<*> {

        //得到原始类型，参数化类型，类型变量
        val genType = obj.javaClass.genericSuperclass
        val typeArguments = (genType as ParameterizedType).actualTypeArguments
        return typeArguments[0] as Class<*>
    }
}