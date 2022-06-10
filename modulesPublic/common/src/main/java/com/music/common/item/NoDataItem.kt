package com.music.common.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.music.common.R
import com.music.common.glide.loadImage
import com.music.common.dazzle.Item


class NoDataItem : Item<NoDataModel>() {

    override fun itemCreate(context: Context, parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.empty_layout, parent, false)
    }

    override fun itemBuild(item: View?, index: Int, data: NoDataModel) {
        item?.findViewById<TextView>(R.id.empty_tip)?.text = data.hint
        item?.findViewById<ImageView>(R.id.empty_icon)?.loadImage(data.src)
    }

}

class NoDataModel(
    val hint: String = "什么都没有~",
    val src: Int = R.drawable.empty_bg
)