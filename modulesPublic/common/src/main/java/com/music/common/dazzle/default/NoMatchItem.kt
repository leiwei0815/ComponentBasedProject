package com.music.common.dazzle.default

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.music.common.R
import com.music.common.dazzle.Item


class NoMatchItem : Item<NoMathData>() {
    override fun itemCreate(context: Context,parent:ViewGroup): View {
        return LayoutInflater.from(context).inflate( R.layout.item_no_match, parent, false)
    }

    override fun itemBuild(item: View?, index: Int, data: NoMathData) {
        item?.findViewById<TextView>(R.id.no_match_tv)?.text = data.type
    }

    override fun performItemBuild(item: View?, index: Int, data: Any) {
        itemBuild(item, index, NoMathData("无法匹配类:${data::class.java}"))
    }
}

class NoMathData(
    val type: String
)