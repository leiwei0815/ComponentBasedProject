package com.music.player

/**
 * @Author   leiwei
 * @Date     2022/5/12
 * @Desc
 */
data class SearchData(val list:List<CourseBrandItemModel>)

data class CourseBrandItemModel(val goodsName:String,val cover :String  = "",val goodsCode:String)